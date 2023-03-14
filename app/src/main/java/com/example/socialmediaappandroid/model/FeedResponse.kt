package com.example.socialmediaappandroid.model

data class FeedResponse(
    val feed: Feed? = null,
    val user: User? = null,
    val images: List<Image>? = null,
    val reaction: Reaction? = null,
)
