package com.example.vacation_project.Screen.Community.PostViewModel


data class Post(
    val id: String = "",
    val title: String = "",
    val subject: String = "",
    val content: String = "",
    val user: String = "",
    val comments: List<Comment> = listOf(),
    var imageUrl: String? = null
)

data class Comment(
    val id: String = "",
    val name: String = "",
    val text: String = ""
)
