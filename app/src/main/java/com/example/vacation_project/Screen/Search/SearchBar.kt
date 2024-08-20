package com.example.vacation_project.Screen.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vacation_project.R

var showTestText by mutableStateOf(false)
@Composable
fun SearchBar(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var isSearchClicked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(24.dp)
            .width(405.dp)
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
                textStyle = TextStyle(color = Color.Black),
                decorationBox = { innerTextField ->
                    if (searchQuery.text.isEmpty()) {
                        Text(
                            text = "검색",
                            color = Color.Black
                        )
                    }
                    innerTextField() // This renders the actual text typed by the user
                }
            )
            IconButton(onClick = { isSearchClicked = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.searchscreen_search),
                    contentDescription = "검색",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    if (isSearchClicked) {
        SearchResult(navController)
    }
}
