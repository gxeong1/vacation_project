package com.example.vacation_project.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vacation_project.MainActivity
import com.google.firebase.auth.FirebaseAuth

class StartActivity : ComponentActivity() {
    // Firebase
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 로그인 시도
        viewModel.tryLogin(this)

        lifecycleScope.launchWhenCreated {
            viewModel.loginResult.collect { isLogin ->
                if (isLogin) {
                    if (auth.currentUser != null) {
                        startActivity(Intent(this@StartActivity, MainActivity::class.java))
                    }
                } else {
                    // 로그인 안되어있을 때 로그인 페이지 열림
                    startActivity(Intent(this@StartActivity, LoginActivity::class.java))
                }
            }
        }
        setContent {
            Surface(color = Color.White) {
                Text(text = "로그인 확인중", fontSize = 30.sp)
            }
        }
    }
}