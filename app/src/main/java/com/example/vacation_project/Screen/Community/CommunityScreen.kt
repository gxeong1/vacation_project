package com.example.vacation_project.Screen.Community

import PostViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes

@Composable
fun CommunityScreen(navController: NavHostController) {
    val viewModel = remember { PostViewModel() }
    val posts by viewModel.posts.collectAsState(emptyList())

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(26.dp))
            Text(
                text = "커뮤니티",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(25.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(posts) { post ->
                    QuestionCard(
                        CardTitle = post.title,
                        Subject = post.subject,
                        User = post.user,
                        onCLick = {
                            navController.navigate("${Routes.PostScreen}/${post.id}") // 게시물 ID를 함께 전달
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        ImageButton(
            imageResId = R.drawable.writebutton,
            contentDescription = "writeButton",
            onClick = {
                navController.navigate(Routes.WriteScreen)
            },
            size = 150,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}


@Composable
fun ImageButton(
    imageResId: Int,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Int
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