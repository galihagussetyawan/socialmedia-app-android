package com.example.socialmediaappandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.databinding.CommentCardItemBinding
import com.example.socialmediaappandroid.model.CommentResponse
import com.example.socialmediaappandroid.ui.viewmodel.CommentViewModel

class CommentAdapter(
    private val context: Context,
    private val commentViewModel: CommentViewModel,
    private val feedId: String
) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private var data: List<CommentResponse>? = null

    fun initData(d: List<CommentResponse>) {
        data = d
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: CommentCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CommentCardItemBinding =
            CommentCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setupCommentCard(holder, position)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    private fun setupCommentCard(holder: ViewHolder, position: Int) {
        with(holder) {
            if (data?.get(position)?.reply != null) {
                binding.lyReply.visibility = View.VISIBLE
                binding.tvReplyUsername.text = data?.get(position)?.reply?.user?.username
            }

            Glide.with(context).load(data?.get(position)?.user?.photoURL)
                .into(binding.ivProfilePicture)
            binding.tvDisplayName.text = data?.get(position)?.user?.displayName
            binding.tvUsername.text = "@" + data?.get(position)?.user?.username
            binding.tvContent.text = data?.get(position)?.text

            //add reply comment
            binding.tvReply.setOnClickListener {
                binding.lyReplyComment.visibility = View.VISIBLE
            }

            binding.btnDiscard.setOnClickListener {
                binding.lyReplyComment.visibility = View.GONE
            }

            binding.tvShowReply.setOnClickListener {
                commentViewModel.getChildrenComments(
                    feedId,
                    position,
                    data?.get(position)?.children!!
                )
                binding.lyChildrenComment.visibility = View.VISIBLE
            }

            //set children comment adapter
            if (data?.get(position)?.children!!.isNotEmpty()) {
                if (data?.get(position)?.children?.get(position)?.text != null) {
                    val replyCommentAdapter =
                        ReplyCommentAdapter(context, data?.get(position)?.children!!)
                    binding.rvReplyCommentList.adapter = replyCommentAdapter
                }
            } else {
                binding.tvShowReply.visibility = View.GONE
            }
        }
    }
}