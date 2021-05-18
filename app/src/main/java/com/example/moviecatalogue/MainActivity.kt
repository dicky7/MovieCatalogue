package com.example.moviecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import com.example.moviecatalogue.adapter.SectionPagerAdapter
import com.example.moviecatalogue.databinding.ActivityMainBinding
import com.example.moviecatalogue.fragment.MovieFragment
import com.example.moviecatalogue.fragment.TvShowFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFragment = listOf(MovieFragment(),TvShowFragment())
        val tittletabs = listOf("Movie", "Tv Show")

        view_pager.adapter = SectionPagerAdapter(listFragment,this.supportFragmentManager, lifecycle)
        TabLayoutMediator(tabs,view_pager){ tablay,position->
            tablay.text = tittletabs[position]
        }.attach()


    }

}