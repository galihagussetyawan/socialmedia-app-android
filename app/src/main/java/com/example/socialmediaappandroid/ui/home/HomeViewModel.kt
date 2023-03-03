package com.example.socialmediaappandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaappandroid.data.network.FeedRepository
import com.example.socialmediaappandroid.data.network.ImageRepository
import com.example.socialmediaappandroid.model.Feed
import com.example.socialmediaappandroid.model.FeedResponse
import com.example.socialmediaappandroid.model.Image
import com.example.socialmediaappandroid.model.User
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {
    private val feedRepository = FeedRepository()
    private val feedData: MutableLiveData<List<FeedResponse>> = MutableLiveData()
    private val imageRepository = ImageRepository()

    fun getFeeds(paramLat: Double, paramLon: Double): LiveData<List<FeedResponse>> {

        viewModelScope.launch {
            val feedResponseList = mutableListOf<FeedResponse>()

            val lat = 0.0144927536231884
            val lon = 0.0181818181818182
            val distance = 10

            val lowerLat = paramLat - lat * distance
            val lowerLon = paramLon - lon * distance

            val greaterLat = paramLat - lat * distance
            val greaterLon = paramLon - lon * distance

            val lesserGeo = GeoPoint(lowerLat, lowerLon)
            val greaterGeo = GeoPoint(greaterLat, greaterLon)

            val users =
                feedRepository.getAllFeeds(lesserGeo, greaterGeo).get().await()
                    .toObjects(Feed::class.java)

            users.forEach {
                val user = it.userId?.get()?.await()?.toObject(User::class.java)
                val images = imageRepository.getFeedImages(it.id.toString()).get().await()
                    .toObjects(Image::class.java)

                feedResponseList.add(FeedResponse(it, user, images))
            }

            feedData.postValue(feedResponseList)
        }

        return feedData
    }
}