package com.example.socialmediaappandroid.data.network

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FeedRepository {
    private val _db = FirebaseFirestore.getInstance()

    fun getAllFeeds(): CollectionReference {
        return _db.collection("feeds")
    }
}