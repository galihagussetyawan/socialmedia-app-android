package com.example.socialmediaappandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.databinding.CommentCardItemBinding
import com.example.socialmediaappandroid.model.CommentResponse

class CommentAdapter(private val context: Context) :
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
        with(holder) {
            Glide.with(context).load(data?.get(position)?.user?.photoURL)
                .into(binding.ivProfilePicture)
            binding.tvDisplayName.text = data?.get(position)?.user?.displayName
            binding.tvUsername.text = "@" + data?.get(position)?.user?.username
            binding.tvContent.text = data?.get(position)?.text
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}