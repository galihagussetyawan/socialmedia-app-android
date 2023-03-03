package com.example.socialmediaappandroid.model

import com.google.firebase.firestore.DocumentId

data class Image(
    @DocumentId val id: String? = null,
    val fileId: String? = null,
    val filePath: String? = null,
    val name: String? = null,
    val thumbnailUrl: String? = null,
    val url: String? = null,
)
