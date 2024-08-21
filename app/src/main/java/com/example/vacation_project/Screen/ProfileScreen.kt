package com.example.vacation_project.Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vacation_project.Login.rememberFirebaseAuthLaunchar
import com.example.vacation_project.R
import com.example.vacation_project.Routes
import com.example.vacation_project.Screen.Community.ImageButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ProfileScreen(navController: NavController, user: FirebaseUser?) {
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    var nickname by remember { mutableStateOf<String?>(null) }
    var commentCount by remember { mutableStateOf(0) }
    var rank by remember { mutableStateOf("1단계") }
    var description by remember { mutableStateOf("심기 전 씨앗") }
    val buttonColor = colorResource(id = R.color.main_color)
    val context = LocalContext.current
    val token = stringResource(id = R.string.client_id)
    val launcher = rememberFirebaseAuthLaunchar(onAuthComplete = { result ->
        user = result.user },
        onAuthError = {
            user = null
        }
    )

    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(user) {
        user?.let {
            db.collection("users").document(it.uid).get().addOnSuccessListener { document ->
                if (document.exists()) {
                    nickname = document.getString("nickname")
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
                        commentCount >= 0 -> {
                            rank = "1단계"
                            description = "심기 전 씨앗"
                        }
                    }
                } else {
                    nickname = "닉네임 없음"
                }
            }.addOnFailureListener {
                Toast.makeText(context, "데이터를 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
                nickname = "닉네임 없음"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
    ) {
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "프로필",
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))

        user?.let {
            Row {
                Column {
                    Text(
                        text = "${nickname ?: "닉네임 로딩 중..."}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 30.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${it.email ?: "이메일 없음"}",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(100.dp))
                Image(painter = painterResource(id = R.drawable.profile),
                    contentDescription = "프로필",
                    modifier = Modifier.size(70.dp))
            }
        } ?: run {
            TextButton(onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(token)
                    .requestEmail()
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context,gso)
                launcher.launch(googleSignInClient.signInIntent)
            }) {
                Text(
                    text = "로그인이 필요합니다.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 등급 카드 표시
        RankCard(rank = rank, description = description, imageResId = when(rank) {
            "4단계" -> R.drawable.rank4
            "3단계" -> R.drawable.rank3
            "2단계" -> R.drawable.rank2
            else -> R.drawable.rank1
        })

        Spacer(modifier = Modifier.height(30.dp))

        Spacer(modifier = Modifier
            .height(5.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "등급 안내", fontSize = 16.sp)
            ImageButton(
                imageResId = R.drawable.back,
                contentDescription = "back",
                onClick = { navController.navigate(Routes.RankScreen) {
                    popUpTo(Routes.ProfileScreen) { inclusive = true }
                    launchSingleTop = true
                } },
                size = 12,
                modifier = Modifier
                    .width(7.dp)
                    .graphicsLayer(scaleX = -1f)
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = {
                    FirebaseAuth.getInstance().signOut()
                    Toast.makeText(context, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .width(345.dp)
                    .height(50.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(buttonColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "로그아웃", color = Color.Black)
            }
        }
    }
}



@Composable
fun RankCard(rank: String, description: String, imageResId: Int) {
    Card(
        modifier = Modifier
            .width(345.dp)
            .height(80.dp)
            .padding(start = 30.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color(0xFF8AB0B9))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 10.dp, start = 10.dp)
        ) {
            Card (modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(Color(0xFFDCEAEB))
                ){
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier.size(55.dp)
                        .padding(top = 7.dp, start = 5.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = rank,
                    fontSize = 13.sp,
                    color = Color.Black
                )
                Text(
                    text = description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}
