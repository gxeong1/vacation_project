package com.example.vacation_project.Login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController : NavHostController){
    Button(onClick = { navController.navigate("name_screen") }) {
        Text(text = "이름 화면 이동")
    }
}