import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vacation_project.Screen.Community.PostViewModel.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PostViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: Flow<List<Post>> get() = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val postsList = withContext(Dispatchers.IO) {
                    firestore.collection("posts")
                        .get()
                        .await()
                        .map { document ->
                            val post = document.toObject(Post::class.java)
                            // 문서 ID를 포함시킵니다.
                            post.copy(id = document.id)
                        }
                }
                _posts.value = postsList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // 게시물 추가 메서드
    fun addPost(title: String, subject: String, content: String, user: String) {
        viewModelScope.launch {
            try {
                val newPost = Post(
                    title = title,
                    subject = subject,
                    content = content,
                    user = user
                )
                // Firestore에 새로운 게시물을 추가합니다.
                val documentReference = firestore.collection("posts").add(newPost).await()
                // ID를 포함하여 업데이트합니다.
                val postId = documentReference.id
                val postWithId = newPost.copy(id = postId)
                _posts.value = _posts.value + postWithId
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
