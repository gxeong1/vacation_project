package com.example.vacation_project.Screen.Community.Post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.vacation_project.R

@Composable
fun Post(title : String, user : String, subject : String, text : String){
    val lineColor = colorResource(id = R.color.line_color)

    Column (modifier = Modifier.padding(start = 22.dp, top = 5.dp)){
        Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.W600)

        Spacer(modifier = Modifier.height(5.dp))

        Row (modifier = Modifier.height(20.dp)){
            Text(text = user,
                fontSize = 14.sp,)

            Spacer(modifier = Modifier.width(5.dp))

            Text(text = "|", fontSize = 14.sp)

            Spacer(modifier = Modifier.width(5.dp))

            Text(text = subject,
                fontSize = 14.sp,)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Spacer(modifier = Modifier
            .height(2.dp)
            .width(350.dp)
            .background(lineColor)
            .align(alignment = Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = text, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(15.dp))
    }


}