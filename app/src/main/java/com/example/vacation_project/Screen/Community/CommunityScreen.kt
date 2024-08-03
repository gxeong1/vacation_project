package com.example.vacation_project.Screen.Community

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes

@Composable
fun CommunityScreen(navController: NavHostController){
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(26.dp))
        Text(text = "커뮤니티",
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
            )
        Spacer(modifier = Modifier.height(25.dp))
        QuestionCard(CardTitle = "제목",
            Subject = "과목",
            Content = "내용",
            onCLick = {
                navController.navigate(Routes.PostScreen)
            })
    }

    Box(modifier = Modifier.size(150.dp)){
        ImageButton(
            imageResId = R.drawable.writebutton,
            contentDescription = "writeButton",
            onClick = {
                navController.navigate(Routes.WriteScreen)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            size = 150
        )
    }
}

@Composable
fun ImageButton(
    imageResId: Int,
    contentDescription: String?,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    size : Int
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier.size(size.dp),
            contentScale = ContentScale.Crop
        )
    }
}