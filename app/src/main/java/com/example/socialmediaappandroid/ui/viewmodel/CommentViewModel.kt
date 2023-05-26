package com.example.socialmediaappandroid.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaappandroid.data.network.CommentRepository
import com.example.socialmediaappandroid.model.Comment
import com.example.socialmediaappandroid.model.CommentResponse
import com.example.socialmediaappandroid.model.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CommentViewModel : ViewModel() {
    private val _commentRepository = CommentRepository()
    private val _comments: MutableLiveData<List<CommentResponse>> = MutableLiveData()

    fun getComments(feedId: String): LiveData<List<CommentResponse>> {
        viewModelScope.launch {
            val result = mutableListOf<CommentResponse>()

            val commentList = _commentRepository.getCommentsByFeedId(feedId).get().await()
                .toObjects(Comment::class.java)

            commentList.forEach {
                val childrenList = mutableListOf<Comment>()
                val replyRef =
                    _commentRepository.getCommentByCommentId(it.reply.toString()).get().await()
                        .toObject(Comment::class.java)
                val userRef = it.user?.get()?.await()?.toObject(User::class.java)

                it?.children?.forEach { childrenItem ->
                    childrenList.add(Comment(childrenItem))
                }

                result.add(
                    CommentResponse(
                        it.id,
                        it.text,
                        replyRef,
                        childrenList,
                        it.createdAt,
                        it.updatedAt,
                        userRef
                    )
                )

                _comments.postValue(result)
            }
        }
        Log.d("comment-viewmodel", _comments.value?.size.toString())
        return _comments
    }

    fun clearComments() {
        _comments.postValue(listOf())
    }
}