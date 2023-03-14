package com.example.socialmediaappandroid.model

import com.google.firebase.firestore.DocumentId

data class Reaction(
    @DocumentId val id: String? = null,
    val feedId: String? = null,
    val userId: String? = null,
    var symbol: Int? = null,
    val createdAt: String? = null
)
