package com.example.socialmediaappandroid.data.network

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class ImageRepository {
    private val _db = FirebaseFirestore.getInstance()

    fun getFeedImages(feedId: String): CollectionReference {
        return _db.collection("feeds").document(feedId).collection("images")
    }
}