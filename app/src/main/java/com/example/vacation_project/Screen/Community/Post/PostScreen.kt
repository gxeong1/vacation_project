package com.example.vacation_project.Screen.Community.Post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Screen.Community.ImageButton

@Composable
fun PostScreen(navController: NavHostController){

    val lineColor = colorResource(id = R.color.line_color)

    Column (modifier = Modifier.background(Color.White)) {
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
                text = "질문",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
            )
            Spacer(
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 24.dp)
            )
        }

        Post(title = "제목", user = "작성자", subject = "과목", text = "내용")

        Spacer(modifier =Modifier.height(15.dp))

        Spacer(modifier = Modifier
            .height(2.dp)
            .width(350.dp)
            .background(lineColor)
            .align(alignment = Alignment.CenterHorizontally))

    }
}