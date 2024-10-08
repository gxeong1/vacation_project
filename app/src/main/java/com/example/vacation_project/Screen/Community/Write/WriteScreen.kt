package com.example.vacation_project.Screen.Community.Write

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.vacation_project.R
import com.example.vacation_project.Routes
import com.example.vacation_project.Screen.Community.ImageButton
import com.example.vacation_project.Screen.Community.PostViewModel.Post
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

@Composable
fun WriteScreen(navController: NavHostController, userId: String) {
    // 상태 변수 정의
    var title by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf<String?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) } // 이미지 URI 상태

    val db = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()

    // 사용자 닉네임 불러오기
    LaunchedEffect(userId) {
        db.collection("users").document(userId).get().addOnSuccessListener { document ->
            if (document.exists()) {
                nickname = document.getString("nickname")
            }
        }
    }

    val buttonColor = colorResource(id = R.color.main_color)
    val context = LocalContext.current

    // 이미지 선택을 위한 ActivityResultLauncher 생성
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }

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
                text = "질문 작성",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
            )
            Spacer(modifier = Modifier
                .size(24.dp)
                .padding(end = 24.dp))
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE3E3E3)))

        EditableTextField(
            value = title,
            onValueChange = { title = it },
            label = "제목",
            size = 44,
            isSingleLine = true
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE3E3E3)))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "과목",
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
            SubjectSelection(
                selectedSubject = subject,
                onSubjectChange = { subject = it }
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE3E3E3)))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                EditableTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = "내용",
                    size = 430,
                    fontsize = 16,
                    isSingleLine = false
                )
            }
            ImageButton(
                imageResId = R.drawable.image,
                contentDescription = "image",
                onClick = { launcher.launch("image/*") }, // 이미지 선택 버튼 클릭 시
                size = 19,
                modifier = Modifier.align(Alignment.Top)
            )
        }

        Button(
            onClick = {
                if (nickname != null) {
                    // Firestore에 게시물 저장
                    val post = Post(
                        title = title,
                        subject = subject,
                        content = content,
                        user = nickname!!,
                        imageUrl = null // 이미지 URL은 나중에 추가
                    )

                    // Firebase Storage에 이미지 업로드
                    imageUri?.let { uri ->
                        val storageRef = storage.reference.child("images/${UUID.randomUUID()}.jpg")
                        storageRef.putFile(uri)
                            .addOnSuccessListener {
                                storageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                                    post.imageUrl = downloadUrl.toString()
                                    // Firestore에 게시물 저장
                                    db.collection("posts")
                                        .add(post)
                                        .addOnSuccessListener {
                                            Log.d("Firestore", "글이 성공적으로 올라갔습니다.")
                                            navController.navigate(Routes.CommunityScreen)
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w("Firestore", "오류 발생", e)
                                        }
                                }
                            }
                            .addOnFailureListener { e ->
                                Log.w("Storage", "이미지 업로드 실패", e)
                            }
                    } ?: run {
                        // 이미지가 선택되지 않은 경우 Firestore에 게시물 저장
                        db.collection("posts")
                            .add(post)
                            .addOnSuccessListener {
                                Log.d("Firestore", "글이 성공적으로 올라갔습니다.")
                                navController.navigate(Routes.CommunityScreen)
                            }
                            .addOnFailureListener { e ->
                                Log.w("Firestore", "오류 발생", e)
                            }
                    }
                } else {
                    Log.w("Firestore", "닉네임을 불러오지 못했습니다.")
                }
            },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .width(345.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(buttonColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "완료", color = Color.Black)
        }
    }
}
