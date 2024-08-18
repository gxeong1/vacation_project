package com.example.vacation_project.Screen.Community.Post

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vacation_project.R

@Composable
fun CommentItem(name: String, text: String) {
    Column(modifier = Modifier.padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Profile Image Placeholder
            Image(
                painter = painterResource(id = R.drawable.profile), // Profile image
                contentDescription = "profile",
                modifier = Modifier.size(35.dp).clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

