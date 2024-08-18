import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vacation_project.R
import com.example.vacation_project.Screen.Community.ImageButton

@Composable
fun WriteComment(onSendClick: (String) -> Unit) {
    var commentText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.sub_color2), shape = RoundedCornerShape(100.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .size(345.dp, 50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = commentText,
            onValueChange = { commentText = it },
            placeholder = { Text(text = "댓글을 입력하세요", color = Color.White) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
        )

        Spacer(modifier = Modifier.size(10.dp))

        ImageButton(
            imageResId = R.drawable.send,
            contentDescription = "send",
            onClick = {
                if (commentText.isNotBlank()) {
                    onSendClick(commentText) // 콜백을 통해 댓글을 전송
                    commentText = "" // 댓글 입력 필드 초기화
                }
            },
            modifier = Modifier.size(24.dp),
            size = 24
        )
    }
}