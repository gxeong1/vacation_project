package com.example.vacation_project.Screen.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.example.vacation_project.R

@Composable
fun SearchScreen(){
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "검색",
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        )
        SearchBar()
    }
}

@Composable
fun SearchBar() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .padding(24.dp)
            .width(345.dp)
            .height(52.dp)
            .clip(CircleShape)
            .background(Color(0xFFE5F2F0)),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            BasicTextField(
                value = searchQuery,
                onValueChange = { newValue -> searchQuery = newValue },
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    // 검색어가 비어있는지 확인할 때 `searchQuery.text`를 사용
                    if (searchQuery.text.isEmpty()) {
                        Text(
                            text = "검색",
                            color = Color.Black
                        )
                    }
                    innerTextField()
                }
            )
            IconButton(onClick = { /* 검색 아이콘 클릭 시 동작 */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.searchscreen_search),  // 검색 아이콘 리소스 경로
                    contentDescription = "검색",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)  // 아이콘 크기를 32dp로 설정
                )
            }
        }
    }
}
