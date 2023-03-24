package com.example.socialmediaappandroid.model

data class FeedResponse(
    var feed: Feed? = null,
    var user: User? = null,
    var images: List<Image>? = null,
    var reaction: Reaction? = null,
)
