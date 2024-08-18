package com.example.vacation_project.Screen.Community.PostViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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
                            document.toObject(Post::class.java)
                        }
                }
                _posts.value = postsList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}