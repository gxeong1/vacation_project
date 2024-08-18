package com.example.vacation_project.Screen.Community.Write

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes
import com.example.vacation_project.Screen.Community.ImageButton
import com.google.firebase.firestore.FirebaseFirestore

val firestore = FirebaseFirestore.getInstance()

fun postContent(title: String, subject: String, content: String) {

    val post = hashMapOf(
        "title" to title,
        "subject" to subject,
        "content" to content,
    )

    firestore.collection("posts")
        .add(post)
        .addOnSuccessListener {
            // 데이터 저장 성공 시
            Log.d("Firestore", "글이 성공적으로 올라갔습니다.")
        }
        .addOnFailureListener { e ->
            // 데이터 저장 실패 시
            Log.w("Firestore", "오류 남", e)
        }
}

@Composable
fun WriteScreen(navController: NavHostController) {
    // 상태 변수 정의
    var title by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    val buttonColor = colorResource(id = R.color.main_color)

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
                onClick = { /* TODO */ },
                size = 19,
                modifier = Modifier.align(Alignment.Top)
            )
        }

        Button(
            onClick = {
                // 포스트 작성 로직 구현
                postContent(title, subject, content)
                navController.navigate(Routes.CommunityScreen)
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
