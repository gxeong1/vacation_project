package com.example.vacation_project.Login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vacation_project.R
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NameScreen(navController: NavController, userId: String) {
    var nickname by remember { mutableStateOf<String?>(null) }
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    val btnColor = colorResource(id = R.color.main_color)

    LaunchedEffect(userId) {
        db.collection("users").document(userId).get().addOnSuccessListener { document ->
            if (document.exists()) {
                nickname = document.getString("nickname")
            } else {
                nickname = ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "사용할 이름을 정해주세요!",
            fontWeight = FontWeight.W600,
        )
        
        Spacer(modifier = Modifier.height(20.dp))

        if (nickname != null) {
            TextField(
                value = nickname ?: "",
                onValueChange = { nickname = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(12.dp)
                    ),
            )
            Button(onClick = {
                if (nickname.isNullOrBlank()) {
                    Toast.makeText(context, "공백은 닉네임으로 설정할 수 없어요!",
                        Toast.LENGTH_SHORT).show()
                } else {
                    val userMap = hashMapOf("nickname" to nickname)
                    db.collection("users").document(userId).set(userMap)
                        .addOnSuccessListener {
                            navController.navigate("main_screen")
                        }
                        .addOnFailureListener {
                            // 실패 시 처리
                        }
                }
            }, modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .width(345.dp)
                .height(50.dp),
                colors = ButtonDefaults.buttonColors(btnColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("완료", color = Color.Black)
            }
        } else {
            Text("Loading...")
        }
    }
}
