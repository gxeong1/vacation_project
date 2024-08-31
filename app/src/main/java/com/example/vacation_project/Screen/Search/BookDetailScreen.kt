package com.example.vacation_project.Screen.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes
import com.example.vacation_project.Screen.Community.ImageButton


@Composable
fun BookDetailScreen(
    navController: NavHostController
) {
    // Extract the book details based on the index
    val bookName = book_name[bookIndex]
    val bookSubject = subject[bookIndex]
    val bookAuthor = author[bookIndex]
    val bookPublishingHouse = publishing_house[bookIndex]
    val bookPrice = price[bookIndex]
    val bookDescription = descrip[bookIndex]
    val bookImage = bookImages[bookIndex]

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
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        // Display the book image
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = bookImage),
                contentDescription = "Book Image",
                modifier = Modifier
                    .size(300.dp)
                    .padding(16.dp)
            )
        }

        // Display the book details

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = bookName, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
        Text(text = "$bookAuthor(지은이), $bookPublishingHouse", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "판매가        $bookPrice 원", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "책 소개", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
        Text(text = "$bookDescription", fontSize = 15.sp, modifier = Modifier.padding(8.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            ReviewButton(navController)
        }
    }

}
@Composable
fun ReviewButton(navController: NavHostController) {
    Button(onClick = { navController.navigate(Routes.ReviewScreen) },
        modifier = Modifier
            .width(345.dp)
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(181, 210, 216, 255)
        )
    ) {
        Text(text = "리뷰쓰기", color = Color.Black)
    }
}