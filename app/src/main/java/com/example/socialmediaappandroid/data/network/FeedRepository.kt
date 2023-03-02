package com.example.socialmediaappandroid.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.Query

class FeedRepository {
    private val _db = FirebaseFirestore.getInstance()

    fun getAllFeeds(lesserGeo: GeoPoint, greaterGeo: GeoPoint): Query {

        val feedRef = _db.collection("feeds")
        feedRef.whereGreaterThan("location", lesserGeo)
        feedRef.whereLessThan("location", greaterGeo)

        return feedRef
    }
}