package com.example.socialmediaappandroid.model

data class CommentResponse(
    var id: String? = null,
    var text: String? = null,
    var reply: CommentResponse? = null,
    var children: List<CommentResponse>? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var user: User? = null
)
