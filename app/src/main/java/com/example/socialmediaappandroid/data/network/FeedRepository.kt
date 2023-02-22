package com.example.socialmediaappandroid.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socialmediaappandroid.model.Feed
import com.google.firebase.firestore.FirebaseFirestore

class FeedRepository {
    private val _db = FirebaseFirestore.getInstance()

    fun getAllFeeds(): LiveData<List<Feed>> {
        val data = MutableLiveData<List<Feed>>()

        _db.collection("feeds")
            .get()
            .addOnSuccessListener {
                data.postValue(it.toObjects(Feed::class.java))
            }

        return data
    }
}