package com.example.vacation_project.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vacation_project.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(navController: NavController, user: FirebaseUser?) {
    val buttonColor = colorResource(id = R.color.main_color)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                // 로그아웃 처리
                FirebaseAuth.getInstance().signOut()

                // 로그아웃 완료 메시지 표시
                Toast.makeText(context, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show()
                navController.popBackStack()

            },
            modifier = Modifier
                .width(345.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(buttonColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "로그 아웃", color = Color.Black)
        }
    }
}
