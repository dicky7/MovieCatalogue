package com.example.moviecatalogue.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.DataMovie
import com.example.moviecatalogue.DetailActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.TvShowAdapter
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.model.MovieCallback
import com.example.moviecatalogue.model.MovieModel
import com.example.moviecatalogue.model.TvCallback
import com.example.moviecatalogue.viewModel.DetailViewModel
import com.example.moviecatalogue.viewModel.DetailViewModel.Companion.TV_SHOW_DETAIL
import com.example.moviecatalogue.viewModel.TvViewModel
import com.example.moviecatalogue.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.list_item.*

class TvShowFragment: Fragment(),TvShowAdapter.OnItemClickCallback{
    lateinit var tvBinding: FragmentTvShowBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tvBinding = FragmentTvShowBinding.inflate(inflater,container,false)
        return tvBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity !=null){

            val factory = ViewModelFactory.getInstance(requireActivity())
            val tvModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            val adapter = TvShowAdapter()
            tvModel.getTvModel().observe(viewLifecycleOwner,{tvShow->
                progresBar(false)
                adapter.setTvShow(tvShow)
                adapter.notifyDataSetChanged()
                adapter.setOnItemClickCallback(this)
            })


            with(tvBinding.rvTvShow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onItemClicked(tvId: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_MOVIE, tvId)
            it.putExtra(DetailActivity.EXTRA_DETAIL, TV_SHOW_DETAIL)
            context?.startActivity(it)
        }
    }
    private fun progresBar(boolean: Boolean){
        tvBinding.progressBar.isVisible = boolean
        tvBinding.progressBar.isInvisible = boolean
    }

}