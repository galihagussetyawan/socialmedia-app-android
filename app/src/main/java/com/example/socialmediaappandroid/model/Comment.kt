package com.example.socialmediaappandroid.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

data class Comment(
    @DocumentId var id: String? = null,
    var text: String? = null,
    var reply: String? = null,
    var children: ArrayList<String>? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var user: DocumentReference? = null
)
