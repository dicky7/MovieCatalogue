package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.api.Network.IMAGE_URL
import com.example.moviecatalogue.data.entity.MovieEntity
import com.example.moviecatalogue.databinding.ListItemBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listMovie = ArrayList<MovieEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    fun setMovie(movie: List<MovieEntity>?){
        if (movie == null)return
        this.listMovie.clear()
        this.listMovie.addAll(movie)
    }

    inner class MovieViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MovieEntity){
            with(binding) {
                listItemTitle.text = movieEntity.title
                listItemRelease.text = movieEntity.date

                Glide.with(itemView.context)
                    .load(IMAGE_URL + movieEntity.image)
                    .transform(RoundedCorners(20))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(listItemImg)

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(movieEntity.id.toString())
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(movieEntity = listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    interface OnItemClickCallback {
        fun onItemClicked(movieId: String)
    }
}