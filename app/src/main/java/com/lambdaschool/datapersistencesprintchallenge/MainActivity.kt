package com.lambdaschool.datapersistencesprintchallenge

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.API_KEY_PARAM
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.SEARCH_MOVIES_ENDPOINT
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {

    companion object {
        val key = API_KEY_PARAM
    }
    lateinit var movieService: MovieApi
    lateinit var newMovie: FavoriteMovie
    lateinit var movieList: MovieSearchResult
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieService = MovieApi.Factory.create()
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        //movieViewModel.getAllMovies().observe(this, Observer { movies -> MovieRepo })

        button_search.setOnClickListener {
            val movieQuery = et_search.text.toString()
            getMovie(movieQuery)
        }

        button_favorites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
            //Toast.makeText(this, "${movieViewModel.getAllMovies().value}", Toast.LENGTH_LONG).show()
            //val list = movieViewModel.getAllMovies()
            //list[0].title
            //Toast.makeText(this, "${list[0].title}", Toast.LENGTH_LONG).show()
        }
    }

    private fun getMovie(query: String) {
        movieService.getMovies(query, key).enqueue(this)
    }

    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
        Toast.makeText(this, "Request Failed", Toast.LENGTH_LONG).show()
        Log.i("DEBUG", t.toString())
    }

    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
        if (response.isSuccessful) {
            Log.i("DEBUG", "${response.body()!!.results[0].original_title}")
            movieList = response.body() as MovieSearchResult
            updateLayout(movieList.results)

        } else {
            Log.i("DEBUG", "reponse $response")
        }
    }

    private fun buildItemView(movie: MovieOverview): TextView {
        var newView = TextView(this)
        newView.text = movie.original_title
        newView.textSize = 32f
        newView.setOnClickListener {
            val newFavoriteMovie = FavoriteMovie(movie.original_title, movie.release_date, false)
            val color = resources.getColor(R.color.colorAccent)
            newView.setBackgroundColor(color)
            movieViewModel.insert(newFavoriteMovie)
        }
        return newView
    }

    fun updateLayout(movies: List<MovieOverview>) {
        ll_movielist.removeAllViews()
        movies.forEach { movie ->
            ll_movielist.addView(buildItemView(movie))
        }
    }
}


