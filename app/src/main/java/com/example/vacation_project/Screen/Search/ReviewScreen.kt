package com.example.vacation_project.Screen.Search

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes
import com.example.vacation_project.Screen.Community.ImageButton
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ReviewScreen(
                navController: NavHostController
) {
    Column (
        modifier = Modifier.background(Color.White),

    ) {
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
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
            Spacer(modifier = Modifier.width(168.dp))
            Text(
                text = "리뷰작성"
            )
        }

        val bookName = book_name[bookIndex]
        val bookAuthor = author[bookIndex]
        val bookPublishingHouse = publishing_house[bookIndex]
        Spacer(modifier = Modifier.height(70.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = bookName, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "$bookAuthor(지은이), $bookPublishingHouse", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))


        // Review input field
        var reviewText by remember { mutableStateOf("") }
        TextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            placeholder = { Text("리뷰를 입력해주세요.") },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.White)  // Apply background color directly here
                .padding(8.dp),           // Optionally add padding if needed
            maxLines = 5
        )


        Spacer(modifier = Modifier.height(150.dp))

        // Submit button
        Row {
            Spacer(Modifier.width(49.dp))
            Button(
                onClick = { navController.navigate(Routes.SearchScreen) },
                modifier = Modifier
                    .width(345.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(181, 210, 216, 255)
                )
            ) {
                Text("완료")
            }
        }
    }

}