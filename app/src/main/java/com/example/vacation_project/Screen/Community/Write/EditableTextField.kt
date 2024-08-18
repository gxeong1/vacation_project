package com.example.vacation_project.Screen.Community.Write

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    size: Int,
    fontsize: Int = 16,
    isSingleLine: Boolean = true
) {
    var isEditing by remember { mutableStateOf(value.isNotEmpty()) }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .height(size.dp)
    ) {
        if (!isEditing && value.isEmpty()) {
            Text(
                text = label,
                fontSize = fontsize.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { isEditing = true }
            )
        } else {
            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    onValueChange(newValue)
                },
                textStyle = TextStyle(
                    fontSize = fontsize.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                singleLine = isSingleLine
            )
        }
    }
}
