package com.rohitkaradkar.imagesearch.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rohitkaradkar.imagesearch.R
import com.rohitkaradkar.imagesearch.databinding.SearchItemBinding
import com.rohitkaradkar.imagesearch.remote.wiki.ImageResult
import com.squareup.picasso.Picasso

class SearchAdapter(
    private val context: Context,
    private val clickListener: (result: ImageResult) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val data = mutableListOf<ImageResult>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<SearchItemBinding>(
            LayoutInflater.from(context),
            R.layout.search_item,
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        if (data.size > position) {
            vh.bind(data[position])
        }
    }

    fun update(newData: List<ImageResult>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageResult: ImageResult) {
            Picasso.get()
                .load(imageResult.url)
                .into(binding.ivImage)

            binding.tvImageTitle.text = imageResult.title
            itemView.setOnClickListener {
                clickListener(imageResult)
            }
        }
    }
}