package com.example.vacation_project.Screen.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes

@Composable
fun Search_menu(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(30.dp),
    ) {
        Row {
                Text(
                    text = "추천순",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.searchscreen_swap), // 위아래 화살표 아이콘
                    contentDescription = "위아래 화살표",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.searchscreen_filter), // 필터 아이콘
                    contentDescription = "필터 아이콘",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.navigate(Routes.FilterScreen)}
                )
        }
    }
}
