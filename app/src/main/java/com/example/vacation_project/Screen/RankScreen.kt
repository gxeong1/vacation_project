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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
//
@Composable
fun RankScreen(navController: NavController, user: FirebaseUser?) {
    val mainColor = colorResource(id = R.color.main_color)
    var commentCount by remember { mutableStateOf(0) }
    var rank by remember { mutableStateOf("1단계") }
    var description by remember { mutableStateOf("심기 전 씨앗") }
    val db = FirebaseFirestore.getInstance()

    // 사용자 댓글 수에 따른 등급 설정
    LaunchedEffect(user) {
        user?.let {
            db.collection("users").document(it.uid).get().addOnSuccessListener { document ->
                if (document.exists()) {
                    commentCount = document.getLong("commentCount")?.toInt() ?: 0

                    // 댓글 수에 따라 단계 설정
                    when {
                        commentCount >= 100 -> {
                            rank = "4단계"
                            description = "다 자란 나무"
                        }
                        commentCount >= 50 -> {
                            rank = "3단계"
                            description = "거의 자란 새싹"
                        }
                        commentCount >= 20 -> {
                            rank = "2단계"
                            description = "조금 자란 새싹"
                        }
                        else -> {
                            rank = "1단계"
                            description = "심기 전 씨앗"
                        }
                    }
                } else {
                    rank = "1단계"
                    description = "심기 전 씨앗"
                }
            }.addOnFailureListener {
                // 실패 시 기본값 설정
                rank = "1단계"
                description = "심기 전 씨앗"
            }
        }
    }

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

        // 댓글 수에 따른 Rank 표시
        Rank(rank = rank, description = description, imageResId = when(rank) {
            "4단계" -> R.drawable.rank4
            "3단계" -> R.drawable.rank3
            "2단계" -> R.drawable.rank2
            else -> R.drawable.rank1
        })

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier
            .fillMaxSize()
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
fun Rank(rank: String, description: String, imageResId: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$rank 단계",
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(painter = painterResource(id = imageResId),
            contentDescription = description,
            modifier = Modifier
                .width(150.dp)
                .height(130.dp))

    }
}
