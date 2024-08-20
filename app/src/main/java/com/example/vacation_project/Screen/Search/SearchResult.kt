package com.example.vacation_project.Screen.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes
import androidx.compose.ui.res.painterResource

var bookIndex = 1
val bookImages: List<Int> = listOf(
    R.drawable.book1, // Replace with actual drawable resource IDs
    R.drawable.book2,
    R.drawable.book3
)
var book_name:List<String> = listOf<String>("정식보카 JUNGSIK VOCA","개념 해결의 법칙 공통수학 1","라이트 매3비 - 매일 지문 3개씩 읽는 비문학 독서 기출")
var subject:List<String> = listOf<String>("영어","수학","국어")
var author:List<String> = listOf<String>("조정식","최용준","안인숙")
var publishing_house:List<String> = listOf<String>("책이로소이다","해법수학연구회","키출판사")
var price:List<Int> = listOf<Int>(16000,16200,17000)
var descrip:List<String> = listOf<String>("'조정식' 수능 영어강사가 만든, 중 고등학생용 기초 영어단어집.","한 줄 꼭 알아야 하는 개념만 쉽고 자세하게 설명. 내신에 최적화된 기본 개념서.","매3비보다 쉬운 고2 수준의 문제 기출")

@Composable
fun SearchResult(navController: NavHostController) {
    Search_menu(navController)
    Column {
        Spacer(modifier = Modifier.height(50.dp))
        book_name.forEachIndexed { index, book ->
            BookItem(
                name = book,
                subject = subject[index],
                author = author[index],
                publishingHouse = publishing_house[index],
                price = price[index],
                description = descrip[index],
                imageResource = bookImages[index], // Pass the image resource directly
                navController = navController, // Pass the navController
                index = index // Pass the index of the book
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BookItem(name: String,
             subject: String,
             author: String,
             publishingHouse: String,
             price: Int,
             description: String,
             imageResource: Int,
             navController: NavHostController,
             index: Int
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            bookIndex=index
            navController.navigate(Routes.BookDetailScreen)
        }
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Book Image",
            modifier = Modifier
                .size(150.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = subject, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text(text = "$author | $publishingHouse", fontSize = 14.sp)
            Text(text = "$price 원", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = description, fontSize = 14.sp)
        }
    }
}