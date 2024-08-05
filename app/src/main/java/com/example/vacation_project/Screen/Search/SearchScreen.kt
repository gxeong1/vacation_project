package com.example.vacation_project.Screen.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes

@Composable
fun SearchScreen(navController: NavHostController){
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "검색",
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        )
        com.example.vacation_project.Screen.SearchBar()
        //이거 왜 그러는 지 모르겠음. 지금 실행 될 수 있게 오류만 수정해놨음.동화 보면 수정해주세요
        Search_menu(navController)
    }
}

