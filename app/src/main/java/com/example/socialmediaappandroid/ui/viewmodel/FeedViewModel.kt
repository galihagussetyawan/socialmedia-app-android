package com.example.socialmediaappandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaappandroid.data.network.FeedRepository
import com.example.socialmediaappandroid.data.network.ImageRepository
import com.example.socialmediaappandroid.model.*
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FeedViewModel : ViewModel() {
    private val feedRepository = FeedRepository()
    private val feedData: MutableLiveData<List<FeedResponse>> = MutableLiveData()
    private val imageRepository = ImageRepository()

    fun getFeeds(paramLat: Double, paramLon: Double, userId: String): LiveData<List<FeedResponse>> {

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

            val feeds =
                feedRepository.getAllFeeds(lesserGeo, greaterGeo).get().await()
                    .toObjects(Feed::class.java)

            feeds.forEach {
                val user = it.userId?.get()?.await()?.toObject(User::class.java)
                val images = imageRepository.getFeedImages(it.id.toString()).get().await()
                    .toObjects(Image::class.java)

                feedResponseList.add(
                    FeedResponse(
                        it,
                        user,
                        images,
                        checkIsReaction(it.id.toString(), userId)
                    )
                )
            }

            feedData.postValue(feedResponseList)
        }

        return feedData
    }

    private suspend fun checkIsReaction(feedId: String, userId: String): Reaction? {
        val reaction =
            feedRepository.checkIsReaction(feedId, userId)
                .get().await().toObjects(Reaction::class.java)

        if (reaction.size == 0) {
            return null
        }

        return reaction[0]
    }

    fun selectEmoticon(position: Int, symbol: Int) {
        if (feedData.value?.get(position)?.reaction?.symbol == null) {
            feedData.postValue(feedData?.value?.toMutableList()?.apply {
                this?.get(position)?.reaction?.symbol = symbol
            })
        } else {
            if (feedData?.value?.get(position)?.reaction?.symbol != symbol) {
                feedData.postValue(feedData?.value?.toMutableList()?.apply {
                    this?.get(position)?.reaction?.symbol = symbol
                })
            }
            if (feedData?.value?.get(position)?.reaction?.symbol == symbol) {
                feedData.postValue(feedData?.value?.toMutableList()?.apply {
                    this?.get(position)?.reaction?.symbol = null
                })
            }
        }
    }
}