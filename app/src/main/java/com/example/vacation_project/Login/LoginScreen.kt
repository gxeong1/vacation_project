package com.example.vacation_project.Login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.vacation_project.Routes
import com.example.vacation_project.ui.theme.AuthState
import com.example.vacation_project.ui.theme.AuthViewModel

@Composable
fun LoginScreen(navController: NavHostController,authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
//    LaunchedEffect(authState.value) {
//        when(authState.value){
//            is AuthState.Authenticated -> navController.navigate("name_screen")
//            is AuthState.Error -> Toast.makeText(context,
//                (authState.value as AuthState.Error).message,Toast.LENGTH_SHORT).show()
//            else -> Unit
//        }
//
//    }이거 있으면 로그인페이지가 안나오네 왜지 없엠 일단

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp, 100.dp)
                .background(color = Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "img")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "이메일") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "비밀번호") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { authViewModel.login(email, password) },
            enabled = authState.value != AuthState.Loading) {
            Text(text = "확인")
        }

        TextButton(onClick = { navController.navigate("sign_screen") }) {
            Text(text = "회원가입하러가기")
        }
    }
}


