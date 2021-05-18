package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.api.Network.IMAGE_URL
import com.example.moviecatalogue.data.entity.TvEntity
import com.example.moviecatalogue.databinding.ListItemBinding

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvViewHolder>(){
    private var listTv = ArrayList<TvEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTvShow(tvShow : List<TvEntity>?){
        if (tvShow == null) return
        this.listTv.clear()
        this.listTv.addAll(tvShow)
    }

    inner class TvViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tvModel: TvEntity){
            with(binding){
                listItemTitle.text = tvModel.title
                listItemRelease.text = tvModel.date

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(tvModel.id.toString())
                }

                Glide.with(itemView.context)
                    .load(IMAGE_URL + tvModel.image )
                    .transform(RoundedCorners(20))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(listItemImg)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return TvViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(tvModel = listTv[position])
    }

    override fun getItemCount(): Int = listTv.size

    interface OnItemClickCallback {
        fun onItemClicked(tvId: String)
    }

}