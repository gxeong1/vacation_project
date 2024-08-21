package com.example.vacation_project.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vacation_project.R

@Composable
fun RankScreen() {

    val mainColor = colorResource(id = R.color.main_color)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "등급",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Rank(rank = "1")

        Spacer(modifier = Modifier.height(20.dp))

        Image(painter = painterResource(id = R.drawable.rank1),
            contentDescription = "rank1",
            modifier = Modifier
                .width(150.dp)
                .height(130.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.fillMaxSize()
            .background(color = mainColor)) {
            Image(
                painter = painterResource(id = R.drawable.ranklevel),
                contentDescription = "ranklavel",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun Rank(rank: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$rank 단계",
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        )
    }
}
