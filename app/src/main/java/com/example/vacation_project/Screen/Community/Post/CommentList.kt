import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vacation_project.Screen.Community.Post.CommentItem
import com.example.vacation_project.Screen.Community.PostViewModel.Comment
import androidx.compose.foundation.lazy.items


@Composable
fun CommentsList(comments: List<Comment>) {
    LazyColumn (modifier = Modifier.height(260.dp)){
        items(comments) { comment ->
            CommentItem(
                name = comment.name,
                text = comment.text
            )
        }
    }
}
