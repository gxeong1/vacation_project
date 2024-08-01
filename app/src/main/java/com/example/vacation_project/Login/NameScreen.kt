package com.example.vacation_project.Login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun NameScreen(navController : NavHostController){
    Button(onClick = { navController.navigate("main_screen") }) {
        Text(text = "메인 화면")
    }
}