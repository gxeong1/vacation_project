package com.example.vacation_project.Screen.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vacation_project.R
import com.example.vacation_project.Routes
import com.example.vacation_project.Screen.Community.ImageButton

// 전역 상태를 위한 배열 선언
val selectedButtons = mutableStateListOf<String>()

@Composable
fun FilterScreen(navController: NavHostController) {
    // 이전 상태를 기억하여 화면 재구성 시 유지
    val selectedButtons = remember { selectedButtons }

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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        SubjectSelection(selectedButtons)
        Spacer(modifier = Modifier.height(50.dp))
        GradeSelection(selectedButtons)
        Spacer(modifier = Modifier.height(50.dp))
        PriceRangeInput()
        Spacer(modifier = Modifier.height(150.dp))
        ApplyButton(navController)
    }
}

@Composable
fun SubjectSelection(selectedButtons: MutableList<String>) {
    val subjects = listOf("국어", "영어", "수학", "사회", "과학", "역사")
    var selectedSubject by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(text = "과목", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        // Splitting the subjects into rows of 3 items each
        subjects.chunked(3).forEach { rowSubjects ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowSubjects.forEach { subject ->
                    Button(
                        onClick = {
                            selectedSubject = subject
                            selectedButtons.clear()
                            selectedButtons.add(subject)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedSubject == subject) Color.Gray else Color(0xFF8AB0B9)
                        ),
                        shape = RoundedCornerShape(11.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 17.dp)
                            .height(49.dp)
                    ) {
                        Text(text = subject, color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun GradeSelection(selectedButtons: MutableList<String>) {
    val grades = listOf("1학년", "2학년", "3학년")
    var selectedGrade by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(text = "학년", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            grades.forEach { grade ->
                Button(
                    onClick = {
                        selectedGrade = grade
                        selectedButtons.clear()
                        selectedButtons.add(grade)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedGrade == grade) Color.Gray else Color(0xFF8AB0B9)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 17.dp)
                        .height(50.dp)
                ) {
                    Text(text = grade, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun PriceRangeInput() {
    var minPrice by remember { mutableStateOf("5000") }
    var maxPrice by remember { mutableStateOf("15000") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(text = "가격", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(63.dp))
            // 최소 가격 입력 필드
            TextField(
                value = minPrice,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) minPrice = newValue
                },
                textStyle = TextStyle(fontSize = 14.sp), // 텍스트 크기 조정
                modifier = Modifier
                    .width(106.dp)  // 필드의 너비를 직접 지정
                    .height(45.dp), // 필드의 높이를 직접 지정
                shape = RoundedCornerShape(12.dp), // 둥근 모서리 설정
                singleLine = true,
                maxLines = 1, // 한 줄로 설정
            )

            Spacer(Modifier.width(24.dp))
            // ~ 표시
            Text(text = " ~ ", fontSize = 20.sp)

            Spacer(Modifier.width(24.dp))
            // 최대 가격 입력 필드
            TextField(
                value = maxPrice,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) maxPrice = newValue
                },
                textStyle = TextStyle(fontSize = 14.sp), // 텍스트 크기 조정
                modifier = Modifier
                    .width(106.dp)  // 필드의 너비를 직접 지정
                    .height(45.dp), // 필드의 높이를 직접 지정
                shape = RoundedCornerShape(12.dp), // 둥근 모서리 설정
                singleLine = true,
                maxLines = 1, // 한 줄로 설정
            )
        }
    }
}

@Composable
fun ApplyButton(navController: NavHostController) {
    Button(onClick = { navController.navigate(Routes.SearchScreen) },
        modifier = Modifier
            .width(345.dp)
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(181, 210, 216, 255)
        )
    ) {
        Text(text = "적용하기", color = Color.Black)
    }
}
