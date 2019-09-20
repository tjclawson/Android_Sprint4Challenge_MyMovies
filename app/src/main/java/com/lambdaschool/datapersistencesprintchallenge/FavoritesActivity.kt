package com.lambdaschool.datapersistencesprintchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    lateinit var movieViewModel: MovieViewModel
    lateinit var favoritesList: LiveData<List<FavoriteMovie>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        favoritesList = movieViewModel.getAllMovies()

        tv_favorites.text = favoritesList.value?.size.toString() ?: "empty"

    }
}
