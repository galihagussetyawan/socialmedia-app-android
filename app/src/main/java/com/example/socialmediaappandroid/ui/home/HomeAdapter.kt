package com.example.socialmediaappandroid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.databinding.FeedCardItemBinding
import com.example.socialmediaappandroid.model.FeedResponse

class HomeAdapter(private val context: Context) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

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

        setupAvatarImage(holder, position)
        setupImageCollection(holder, position)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class ViewHolder(val binding: FeedCardItemBinding) : RecyclerView.ViewHolder(binding.root)

    private fun setupAvatarImage(holder: ViewHolder, position: Int) {
        with(holder) {
            try {

                Glide.with(context)
                    .load(data?.get(position)?.user?.photoURL)
                    .into(binding.avatarImage)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setupImageCollection(holder: ViewHolder, position: Int) {
        with(holder) {
            if (data?.get(position)?.images?.size!! > 0) {
                binding.cardSingleImage

                Glide.with(context)
                    .load(data?.get(position)?.images?.get(0)?.url)
                    .into(binding.cardSingleImage.ivFirstImage)

            } else {
                binding.flImageCollection.visibility = View.GONE
            }
        }
    }
}