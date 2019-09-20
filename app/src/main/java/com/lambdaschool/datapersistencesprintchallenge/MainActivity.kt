package com.lambdaschool.datapersistencesprintchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.API_KEY_PARAM
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.SEARCH_MOVIES_ENDPOINT
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {

    companion object {
        val path = SEARCH_MOVIES_ENDPOINT
        val key = API_KEY_PARAM
    }
    lateinit var movieService: MovieApi
    lateinit var newMovie: FavoriteMovie
    lateinit var movieList: MovieSearchResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieService = MovieApi.Factory.create()

        button_search.setOnClickListener {
            val movieQuery = et_search.text.toString()
            getMovie(movieQuery)
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
        } else {
            Log.i("DEBUG", "reponse $response")
        }
    }
}


