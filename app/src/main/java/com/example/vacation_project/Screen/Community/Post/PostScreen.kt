package com.example.vacation_project.Screen.Community.Post

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Screen.Community.ImageButton
import com.example.vacation_project.Screen.Community.PostViewModel.Post
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PostScreen(navController: NavHostController, postId: String) {
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var post by remember { mutableStateOf<Post?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // 게시물 데이터 가져오기
    LaunchedEffect(postId) {
        db.collection("posts").document(postId).get().addOnSuccessListener { document ->
            if (document.exists()) {
                post = document.toObject(Post::class.java)
            }
            isLoading = false
        }.addOnFailureListener { exception ->
            Log.w("PostScreen", "Error getting document.", exception)
            isLoading = false
        }
    }

    if (isLoading) {
        // 로딩 중 UI
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        post?.let {
            Column(modifier = Modifier.background(Color.White)) {
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

                Spacer(modifier = Modifier.height(15.dp))

                Spacer(modifier = Modifier
                    .height(2.dp)
                    .width(350.dp)
                    .background(colorResource(id = R.color.line_color))
                    .align(alignment = Alignment.CenterHorizontally))

                Comment(name = "닉네임", write = "답변 " + "\n" + "내용", rankRes = R.drawable.rank, profileRes = R.drawable.profile)

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .size(345.dp, 50.dp)
                            .background(colorResource(id = R.color.sub_color2), shape = RoundedCornerShape(100.dp))
                            .align(Alignment.BottomCenter)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.commentwrite),
                            contentDescription = "comment write",
                            modifier = Modifier.size(40.dp)
                                .padding(start = 10.dp, top = 12.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "답변 작성", color = Color.White, fontSize = 15.sp,
                            modifier = Modifier.padding(top = 12.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        ImageButton(
                            imageResId = R.drawable.send,
                            contentDescription = "send",
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(end = 10.dp),
                            size = 24
                        )
                    }
                }
            }
        } ?: run {
            // 게시물이 없거나 로딩 실패 시 표시할 UI
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("게시물을 불러오는 데 실패했습니다.")
            }
        }
    }
}
