package com.example.vacation_project.Screen.Community.Post

import CommentsList
import WriteComment
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.vacation_project.R
import com.example.vacation_project.Screen.Community.ImageButton
import com.example.vacation_project.Screen.Community.PostViewModel.Comment
import com.example.vacation_project.Screen.Community.PostViewModel.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore



@Composable
fun PostScreen(navController: NavHostController, postId: String) {
    val db = FirebaseFirestore.getInstance()
    var post by remember { mutableStateOf<Post?>(null) }
    var comments by remember { mutableStateOf<List<Comment>>(listOf()) }
    var isLoading by remember { mutableStateOf(true) }
    var postUserName by remember { mutableStateOf("") }

    LaunchedEffect(postId) {
        db.collection("posts").document(postId).get().addOnSuccessListener { document ->
            if (document.exists()) {
                post = document.toObject(Post::class.java)
                postUserName = post?.user ?: "" // 포스트의 사용자 이름을 가져옵니다
                fetchComments(postId) { fetchedComments ->
                    comments = fetchedComments
                    isLoading = false
                }
            }
        }.addOnFailureListener { exception ->
            Log.w("PostScreen", "Error getting document.", exception)
            isLoading = false
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        post?.let {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)) {

                // 상단 내용
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ImageButton(
                        imageResId = R.drawable.back,
                        contentDescription = "back",
                        onClick = { navController.popBackStack() },
                        size = 12,
                        modifier = Modifier
                            .padding(start = 22.dp)
                            .width(7.dp)
                    )
                    Text(
                        text = "질문",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                    )
                    Spacer(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 24.dp)
                    )
                }

                Post(
                    title = it.title,
                    user = it.user,
                    subject = it.subject,
                    text = it.content
                )

                // 이미지 표시
                it.imageUrl?.let { url ->
                    Image(painter = rememberImagePainter(url),
                        contentDescription = "Post Image",
                        modifier = Modifier.fillMaxWidth())
                }

                Spacer(modifier = Modifier.height(15.dp))

                Spacer(modifier = Modifier
                    .height(2.dp)
                    .width(350.dp)
                    .background(colorResource(id = R.color.line_color))
                    .align(alignment = Alignment.CenterHorizontally))

                CommentsList(comments)

                Spacer(modifier = Modifier.height(15.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp), // Adjust padding as needed
                    contentAlignment = Alignment.BottomCenter
                ) {
                    WriteComment { commentText ->
                        val newComment = Comment(name = postUserName, text = commentText)
                        addComment(postId, newComment)  // 댓글 추가
                        fetchComments(postId) { fetchedComments ->
                            comments = fetchedComments
                        }
                    }
                }
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("게시물을 불러오는 데 실패했습니다.")
            }
        }
    }
}


// 댓글 편집
private fun fetchComments(postId: String, onResult: (List<Comment>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("posts").document(postId).collection("comments")
        .get()
        .addOnSuccessListener { result ->
            val commentsList = result.map { document ->
                document.toObject(Comment::class.java).copy(id = document.id)
            }
            onResult(commentsList)
        }
        .addOnFailureListener { exception ->
            Log.w("PostScreen", "Error getting comments.", exception)
            onResult(emptyList())  // 실패 시 빈 리스트 반환
        }
}

private fun addComment(postId: String, comment: Comment) {
    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser

    if (user != null) {
        val userDoc = db.collection("users").document(user.uid)

        // 댓글을 posts/{postId}/comments 하위 컬렉션에 추가
        db.collection("posts").document(postId).collection("comments")
            .add(comment)
            .addOnSuccessListener { documentReference ->
                Log.d("PostScreen", "Comment added with ID: ${documentReference.id}")

                // 댓글 수를 증가시킵니다.
                userDoc.update("commentCount", FieldValue.increment(1))
                    .addOnSuccessListener {
                        Log.d("PostScreen", "User comment count updated")
                    }
                    .addOnFailureListener { e ->
                        Log.w("PostScreen", "Error updating user comment count", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.w("PostScreen", "Error adding comment", e)
            }
    } else {
        Log.w("PostScreen", "User is not logged in")
    }
}
