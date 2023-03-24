package com.example.socialmediaappandroid.model

import com.google.firebase.firestore.DocumentId

data class Reaction(
    @DocumentId var id: String? = null,
    var feedId: String? = null,
    var userId: String? = null,
    var symbol: Int? = null,
    var createdAt: String? = null
)
