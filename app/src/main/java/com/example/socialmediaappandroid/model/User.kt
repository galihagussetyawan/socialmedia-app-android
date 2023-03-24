package com.example.socialmediaappandroid.model

import com.google.firebase.firestore.DocumentId

data class User(
    @DocumentId var id: String? = null,
    var username: String? = null,
    var displayName: String? = null,
    var isPrivate: Boolean? = null,
    var photoURL: String? = null
)
