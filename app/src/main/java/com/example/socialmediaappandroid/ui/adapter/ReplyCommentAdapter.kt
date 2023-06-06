package com.example.socialmediaappandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.databinding.ReplyCommentCardItemBinding
import com.example.socialmediaappandroid.model.CommentResponse

class ReplyCommentAdapter(private val context: Context, var data: List<CommentResponse>) :
    RecyclerView.Adapter<ReplyCommentAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ReplyCommentCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ReplyCommentCardItemBinding = ReplyCommentCardItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setupCommentCard(holder, position)
    }

    override fun getItemCount(): Int {
        return data.size
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
        }
    }
}