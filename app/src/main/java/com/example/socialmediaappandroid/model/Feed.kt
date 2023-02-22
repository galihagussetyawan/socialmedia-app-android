package com.example.socialmediaappandroid.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.GeoPoint

data class Feed(
    @DocumentId val id: String? = null,
    val text: String? = null,
    val createdAt: String? = null,
    val location: GeoPoint? = null,
    val userId: DocumentReference? = null
)
