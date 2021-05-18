package com.example.moviecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.model.MovieModel
import com.example.moviecatalogue.viewModel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_DETAIL = "extra_detail"
    }
    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.hide()
        detailBinding.tolbar.setNavigationOnClickListener { onBackPressed() }

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null){
            val movieId = extras.getString(EXTRA_MOVIE)
            val detailId = extras.getString(EXTRA_DETAIL)

            if (movieId != null && detailId != null){
                viewModel.setMovieDetail(movieId,detailId)
                val movie = viewModel.getMovieDetail()

            }
        }
    }

    private fun bind(movieModel: MovieModel){
        with(detailBinding){

            titleDetail.text = movieModel.title
            genreDetail.text = movieModel.genre
            releaseDetail.text = movieModel.realese
            overviewDetail.text = movieModel.desc
        }
        Glide.with(this)
            .load(movieModel.poster)
            .into(detailBinding.imgDetail)
    }
}