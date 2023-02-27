package com.example.socialmediaappandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialmediaappandroid.databinding.FeedCardItemBinding
import com.example.socialmediaappandroid.model.FeedResponse

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var data: List<FeedResponse>? = null

    fun setData(d: List<FeedResponse>) {
        data = d
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: FeedCardItemBinding =
            FeedCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.displayName.text = data?.get(position)?.user?.displayName
            binding.textBody.text = data?.get(position)?.feed?.text
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class ViewHolder(val binding: FeedCardItemBinding) : RecyclerView.ViewHolder(binding.root)
}