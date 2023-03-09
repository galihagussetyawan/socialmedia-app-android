package com.example.socialmediaappandroid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.R
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
        setupEmoReact(holder)
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

            if (data!![position].images!!.isEmpty()) {
                binding.flImageCollection.visibility = View.GONE
            }

            if (data!![position].images!!.size == 1) {
                binding.cardSingleImage.root.visibility = View.VISIBLE

                Glide.with(context)
                    .load(data!![position].images?.get(0)?.url)
                    .into(binding.cardSingleImage.ivFirstImage)
            }

            if (data!![position].images!!.size == 2) {
                binding.cardDoubleImage.root.visibility = View.VISIBLE

                Glide.with(context)
                    .load(data!![position].images?.get(0)?.url)
                    .into(binding.cardDoubleImage.ivFirstImage)

                Glide.with(context)
                    .load(data!![position].images?.get(1)?.url)
                    .into(binding.cardDoubleImage.ivSecondImage)
            }


            if (data!![position].images!!.size == 3) {
                binding.cardTripleImage.root.visibility = View.VISIBLE

                Glide.with(context)
                    .load(data!![position].images?.get(0)?.url)
                    .into(binding.cardTripleImage.ivFirstImage)

                Glide.with(context)
                    .load(data!![position].images?.get(1)?.url)
                    .into(binding.cardTripleImage.ivSecondImage)

                Glide.with(context)
                    .load(data!![position].images?.get(2)?.url)
                    .into(binding.cardTripleImage.ivThirdImage)
            }

            if (data!![position].images!!.size == 4) {
                binding.cardQuadImage.root.visibility = View.VISIBLE

                Glide.with(context)
                    .load(data!![position].images?.get(0)?.url)
                    .into(binding.cardQuadImage.ivFirstImage)

                Glide.with(context)
                    .load(data!![position].images?.get(1)?.url)
                    .into(binding.cardQuadImage.ivSecondImage)

                Glide.with(context)
                    .load(data!![position].images?.get(2)?.url)
                    .into(binding.cardQuadImage.ivThirdImage)

                Glide.with(context)
                    .load(data!![position].images?.get(3)?.url)
                    .into(binding.cardQuadImage.ivFourthImage)
            }

            if (data!![position].images!!.size > 4) {
                binding.cardMoreImage.root.visibility = View.VISIBLE

                Glide.with(context)
                    .load(data!![position].images?.get(0)?.url)
                    .into(binding.cardMoreImage.ivFirstImage)

                Glide.with(context)
                    .load(data!![position].images?.get(1)?.url)
                    .into(binding.cardMoreImage.ivSecondImage)

                Glide.with(context)
                    .load(data!![position].images?.get(2)?.url)
                    .into(binding.cardMoreImage.ivThirdImage)

                Glide.with(context)
                    .load(data!![position].images?.get(3)?.url)
                    .into(binding.cardMoreImage.ivMoreImage)
            }
        }
    }

    fun setupEmoReact(holder: ViewHolder) {
        with(holder) {
            binding.btnEmo1.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.loginFragment)
            }
        }
    }
}