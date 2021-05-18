package com.example.moviecatalogue

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val movieData = DataMovie.generateDataMovie()
    private val tvData = DataMovie.generateDataTv()

    //Rule
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    //Test List Movie
    @Test
    fun showlistMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieData.size))
    }

    //test show detail movie
    @Test
    fun showdetailMovie() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(movieData[0].title)))
        onView(withId(R.id.genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_detail)).check(matches(withText(movieData[0].genre)))
        onView(withId(R.id.release_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.release_detail)).check(matches(withText(movieData[0].realese)))
        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(withText(movieData[0].desc)))
    }

    //Test ShowListTv
    @Test
    fun showlistTv() {
        Thread.sleep(5000)
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvData.size))
    }

    //test show detail tvShow
    @Test
    fun showdetailTv() {
        Thread.sleep(5000)
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.title_detail)).check(matches(withText(tvData[0].title)))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_detail)).check(matches(withText(tvData[0].genre)))
        onView(withId(R.id.release_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.release_detail)).check(matches(withText(tvData[0].realese)))
        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_detail)).check(matches(withText(tvData[0].desc)))
    }
    
}