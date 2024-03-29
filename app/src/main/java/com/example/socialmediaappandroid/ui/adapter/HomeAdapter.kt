package com.example.socialmediaappandroid.ui.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.R
import com.example.socialmediaappandroid.databinding.FeedCardItemBinding
import com.example.socialmediaappandroid.model.FeedResponse
import com.example.socialmediaappandroid.ui.viewmodel.FeedViewModel

class HomeAdapter(private val context: Context, private val feedViewModel: FeedViewModel) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var data: List<FeedResponse>? = null

    fun setData(d: List<FeedResponse>) {
        data = d
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

            val bundle = bundleOf("position" to position.toString())

            binding.textBody.setOnClickListener() {
                Navigation.findNavController(holder.itemView).navigate(R.id.feedFragment, bundle)
            }

            binding.displayName.setOnClickListener() {
                Navigation.findNavController(holder.itemView).navigate(R.id.profileFragment)
            }
        }

        setupAvatarImage(holder, position)
        setupImageCollection(holder, position)
        setupEmoReact(holder, position)
        selectEmoticon(holder, position)
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

    private fun setupEmoReact(holder: ViewHolder, position: Int) {
        val color = TypedValue().let {
            context.theme.resolveAttribute(android.R.attr.windowBackground, it, true)
            context.getColor(it.resourceId)
        }

        with(holder) {
            when (data?.get(position)?.reaction?.symbol) {
                1 -> {
                    binding.btnEmo1.background.setTint(context.getColor(R.color.green))
                }
                2 -> {
                    binding.btnEmo2.background.setTint(context.getColor(R.color.green))
                }
                3 -> {
                    binding.btnEmo3.background.setTint(context.getColor(R.color.green))
                }
                4 -> {
                    binding.btnEmo4.background.setTint(context.getColor(R.color.green))
                }
                5 -> {
                    binding.btnEmo5.background.setTint(context.getColor(R.color.green))
                }
                6 -> {
                    binding.btnEmo6.background.setTint(context.getColor(R.color.green))
                }
                7 -> {
                    binding.btnEmo7.background.setTint(context.getColor(R.color.green))
                }
                else -> {
                    binding.btnEmo1.background.setTint(color)
                    binding.btnEmo2.background.setTint(color)
                    binding.btnEmo3.background.setTint(color)
                    binding.btnEmo4.background.setTint(color)
                    binding.btnEmo5.background.setTint(color)
                    binding.btnEmo6.background.setTint(color)
                    binding.btnEmo7.background.setTint(color)
                }
            }
        }
    }

    private fun selectEmoticon(holder: ViewHolder, position: Int) {
        val color = TypedValue().let {
            context.theme.resolveAttribute(android.R.attr.windowBackground, it, true)
            context.getColor(it.resourceId)
        }

        with(holder) {

            binding.btnEmo1.setOnClickListener {
                feedViewModel.selectEmoticon(position, 1)

                binding.btnEmo2.background.setTint(color)
                binding.btnEmo3.background.setTint(color)
                binding.btnEmo4.background.setTint(color)
                binding.btnEmo5.background.setTint(color)
                binding.btnEmo6.background.setTint(color)
                binding.btnEmo7.background.setTint(color)

                notifyDataSetChanged()
            }

            binding.btnEmo2.setOnClickListener {
                feedViewModel.selectEmoticon(position, 2)

                binding.btnEmo1.background.setTint(color)
                binding.btnEmo3.background.setTint(color)
                binding.btnEmo4.background.setTint(color)
                binding.btnEmo5.background.setTint(color)
                binding.btnEmo6.background.setTint(color)
                binding.btnEmo7.background.setTint(color)

                notifyDataSetChanged()
            }

            binding.btnEmo3.setOnClickListener {
                feedViewModel.selectEmoticon(position, 3)

                binding.btnEmo2.background.setTint(color)
                binding.btnEmo1.background.setTint(color)
                binding.btnEmo4.background.setTint(color)
                binding.btnEmo5.background.setTint(color)
                binding.btnEmo6.background.setTint(color)
                binding.btnEmo7.background.setTint(color)

                notifyDataSetChanged()
            }

            binding.btnEmo4.setOnClickListener {
                feedViewModel.selectEmoticon(position, 4)

                binding.btnEmo1.background.setTint(color)
                binding.btnEmo2.background.setTint(color)
                binding.btnEmo3.background.setTint(color)
                binding.btnEmo5.background.setTint(color)
                binding.btnEmo6.background.setTint(color)
                binding.btnEmo7.background.setTint(color)

                notifyDataSetChanged()
            }

            binding.btnEmo5.setOnClickListener {
                feedViewModel.selectEmoticon(position, 5)

                binding.btnEmo1.background.setTint(color)
                binding.btnEmo2.background.setTint(color)
                binding.btnEmo3.background.setTint(color)
                binding.btnEmo4.background.setTint(color)
                binding.btnEmo6.background.setTint(color)
                binding.btnEmo7.background.setTint(color)

                notifyDataSetChanged()
            }

            binding.btnEmo6.setOnClickListener {
                feedViewModel.selectEmoticon(position, 6)

                binding.btnEmo1.background.setTint(color)
                binding.btnEmo2.background.setTint(color)
                binding.btnEmo3.background.setTint(color)
                binding.btnEmo4.background.setTint(color)
                binding.btnEmo5.background.setTint(color)
                binding.btnEmo7.background.setTint(color)

                notifyDataSetChanged()
            }

            binding.btnEmo7.setOnClickListener {
                feedViewModel.selectEmoticon(position, 7)

                binding.btnEmo1.background.setTint(color)
                binding.btnEmo2.background.setTint(color)
                binding.btnEmo3.background.setTint(color)
                binding.btnEmo4.background.setTint(color)
                binding.btnEmo5.background.setTint(color)
                binding.btnEmo6.background.setTint(color)

                notifyDataSetChanged()
            }
        }

    }
}