package com.example.socialmediaappandroid.model

import com.google.firebase.firestore.DocumentId

data class User(
    @DocumentId val id: String? = null,
    val username: String? = null,
    val displayName: String? = null,
    val isPrivate: Boolean? = null,
    val photoURL: String? = null
)
