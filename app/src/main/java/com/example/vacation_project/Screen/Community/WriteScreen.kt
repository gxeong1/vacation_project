package com.example.vacation_project.Screen.Community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes

@Composable
fun WriteScreen(navController: NavHostController) {

    val buttonColor = colorResource(id = R.color.main_color)

    Column (modifier = Modifier.background(Color.White)){
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
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
                text = "질문 작성",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                )
            Spacer(modifier = Modifier
                .size(24.dp)
                .padding(end = 24.dp))
            }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE3E3E3)))

        EditableTextField("제목", 44,20, true)

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE3E3E3)))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "과목",
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
            SubjectSelection()
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE3E3E3)))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                EditableTextField(
                    title = "내용",
                    size = 400,
                    fontsize = 16,
                    isSingleLine = false
                )
            }
            ImageButton(
                imageResId = R.drawable.image,
                contentDescription = "image",
                onClick = { /* TODO */ },
                size = 19,
                modifier = Modifier.align(Alignment.Top)
            )
        }



        Button(onClick = { navController.navigate(Routes.CommunityScreen) },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .width(345.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(buttonColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "완료", color = Color.Black)
        }
        }
    }

