package com.example.moviecatalogue.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.DetailActivity
import com.example.moviecatalogue.adapter.MovieAdapter
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.viewModel.DetailViewModel.Companion.MOVIE_DETAIL
import com.example.moviecatalogue.viewModel.MovieViewModel
import com.example.moviecatalogue.viewModel.ViewModelFactory


class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {
    private lateinit var movieBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieBinding = FragmentMovieBinding.inflate(inflater,container,false)
        return movieBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            progresBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val movieModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            movieAdapter = MovieAdapter()

            movieModel.getMovieModel().observe(viewLifecycleOwner,{movie->
                progresBar(false)
                movieAdapter.setMovie(movie)
                movieAdapter.notifyDataSetChanged()
                movieAdapter.setOnItemClickCallback(this)
            })

            movieBinding.rvMovies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onItemClicked(movieId: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_MOVIE, id)
            it.putExtra(DetailActivity.EXTRA_DETAIL, MOVIE_DETAIL)
            context?.startActivity(it)
        }
    }

    private fun progresBar(boolean: Boolean){
        movieBinding.progressBar.isVisible = boolean
        movieBinding.progressBar.isInvisible = boolean
    }

}