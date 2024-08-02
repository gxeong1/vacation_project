package com.example.vacation_project.Screen.Community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vacation_project.R

@Composable
fun QuestionCard(
    CardTitle : String,
    Subject : String,
    Content : String
){
    val backgroundColor = colorResource(id = R.color.sub_color)
    Column(modifier = Modifier
        .width(345.dp)
        .height(106.dp)
        .background(backgroundColor, RoundedCornerShape(12.dp))
        .padding(top = 16.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
    ) {
        Text(text = CardTitle, fontSize = 18.sp, fontWeight = FontWeight.W600)
        Text(text = Subject, fontSize = 14.sp, fontWeight = FontWeight.W400)
        Text(text = Content, fontSize = 16.sp, fontWeight = FontWeight.W400)

    }
}