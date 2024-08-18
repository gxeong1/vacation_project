package com.example.vacation_project.Screen.Community.Write

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.vacation_project.R

@Composable
fun SubjectSelection(
    selectedSubject: String,
    onSubjectChange: (String) -> Unit
) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(end = 10.dp)
        .fillMaxWidth()
    ) {
        Button(
            onClick = { isDropDownMenuExpanded = !isDropDownMenuExpanded },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .width(100.dp)
                .align(Alignment.End)
        ) {
            Row {
                Text(text = selectedSubject, color = Color.Black, fontSize = 15.sp)
                Image(
                    painter = painterResource(id = R.drawable.selection),
                    contentDescription = "select",
                    modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp)
                )
            }
        }

        if (isDropDownMenuExpanded) {
            Popup(
                onDismissRequest = { isDropDownMenuExpanded = false },
                alignment = Alignment.TopEnd
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.White)
                        .border(1.dp, Color.Black)
                        .padding(8.dp)
                        .width(100.dp)
                ) {
                    listOf("국어", "수학", "영어", "사회", "과학", "역사").forEach { subject ->
                        Text(
                            text = subject,
                            modifier = Modifier
                                .clickable {
                                    onSubjectChange(subject)
                                    isDropDownMenuExpanded = false
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
