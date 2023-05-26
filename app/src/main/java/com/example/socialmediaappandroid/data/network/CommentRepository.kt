package com.example.socialmediaappandroid.data.network

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class CommentRepository {
    private val _db = FirebaseFirestore.getInstance()

    fun getCommentsByFeedId(feedId: String): Query {
        return _db.collection("feeds").document(feedId).collection("comments")
            .whereEqualTo("reply", null)
    }

    fun getCommentByCommentId(commentId: String): DocumentReference {
        return _db.collection("comments").document(commentId)
    }
}