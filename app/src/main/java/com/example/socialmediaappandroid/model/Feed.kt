package com.example.socialmediaappandroid.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.GeoPoint

data class Feed(
    @DocumentId var id: String? = null,
    var text: String? = null,
    var createdAt: String? = null,
    var location: GeoPoint? = null,
    var userId: DocumentReference? = null
)
