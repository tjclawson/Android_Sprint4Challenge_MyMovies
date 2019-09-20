package com.lambdaschool.datapersistencesprintchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    lateinit var movieViewModel: MovieViewModel
    lateinit var favoritesList: List<FavoriteMovie>
    lateinit var repo: MovieRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        favoritesList = movieViewModel.getAllMovies()

        updateLayout(favoritesList)

    }

    private fun buildItemView(movie: FavoriteMovie): TextView {
        var newView = TextView(this)
        newView.text = movie.title
        newView.textSize = 24f
        val colorWatched = resources.getColor(R.color.colorAccent)
        val colorNotWatched = resources.getColor(R.color.colorPrimary)
        if (movie.watched == true) {
            newView.setBackgroundColor(colorWatched)
        } else {
            newView.setBackgroundColor(colorNotWatched)
        }
        newView.setOnClickListener {
            if (movie.watched == false) {
                movie.watched = true
                movieViewModel.update(movie)
                newView.setBackgroundColor(colorWatched)
            } else {
                movie.watched = false
                movieViewModel.update(movie)
                newView.setBackgroundColor(colorNotWatched)
            }
        }
        newView.setOnLongClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Movie")
            builder.setMessage("Are you sure you want to remove this movie from your favorites?")
            builder.setPositiveButton("YES"){dialog, which ->
                movieViewModel.delete(movie)
                favoritesList = movieViewModel.getAllMovies()
                updateLayout(favoritesList)
            }

            builder.setNegativeButton("NO") {_, _ -> }

            val dialog: AlertDialog = builder.create()
            dialog.show()

            true
        }

        return newView
    }

    fun updateLayout(movies: List<FavoriteMovie>) {
        ll_favoritelist.removeAllViews()
        movies.forEach { movie ->
            ll_favoritelist.addView(buildItemView(movie))
        }
    }
}
