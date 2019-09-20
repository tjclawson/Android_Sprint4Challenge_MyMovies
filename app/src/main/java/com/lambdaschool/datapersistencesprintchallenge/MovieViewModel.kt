package com.lambdaschool.datapersistencesprintchallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

class MovieViewModel (application: Application) : AndroidViewModel(application) {

    private var repo: MovieRepo = MovieRepo(application)
    private var movieList: LiveData<List<FavoriteMovie>> = repo.getAllMovies()

    fun insert(movie: FavoriteMovie) {
        repo.insert(movie)
    }

    fun update(movie: FavoriteMovie) {
        repo.update(movie)
    }

    fun delete(movie: FavoriteMovie) {
        repo.delete(movie)
    }

    fun getAllMovies(): LiveData<List<FavoriteMovie>> {
        return movieList
    }
}