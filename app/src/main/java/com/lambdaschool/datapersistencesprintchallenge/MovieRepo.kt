package com.lambdaschool.datapersistencesprintchallenge

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

class MovieRepo(context: Context) {
    private var movieDao: MovieDao
    private var movieList: LiveData<List<FavoriteMovie>>

    init {
        val database: MovieDatabase = MovieDatabase.getInstance(context)!!
        movieDao = database.movieDao()
        movieList = movieDao.getAllMovies()
    }

    fun insert(movie: FavoriteMovie) {
        val insertMovieAsyncTask = InsertMovieAsyncTask(movieDao).execute(movie)
    }

    fun update(movie: FavoriteMovie) {
        val updateMovieAsyncTask = UpdateMovieAsyncTask(movieDao).execute(movie)
    }

    fun delete(movie: FavoriteMovie) {
        val deleteMovieAsyncTask = DeleteMovieAsyncTask(movieDao).execute(movie)
    }

    fun getAllMovies(): LiveData<List<FavoriteMovie>> {
        return movieList
    }

    companion object {

        private class InsertMovieAsyncTask(movieDao: MovieDao) : AsyncTask<FavoriteMovie, Unit, Unit>() {
            val MovieDao = movieDao

            override fun doInBackground(vararg p0: FavoriteMovie?) {
                MovieDao.insert(p0[0]!!)
            }
        }

        private class UpdateMovieAsyncTask(movieDao: MovieDao) : AsyncTask<FavoriteMovie, Unit, Unit>() {
            val MovieDao = movieDao

            override fun doInBackground(vararg p0: FavoriteMovie?) {
                MovieDao.update(p0[0]!!)
            }
        }

        private class DeleteMovieAsyncTask(movieDao: MovieDao) : AsyncTask<FavoriteMovie, Unit, Unit>() {
            val MovieDao = movieDao

            override fun doInBackground(vararg p0: FavoriteMovie?) {
                MovieDao.delete(p0[0]!!)
            }
        }

        private class AllPokemontMovieAsyncTask(movieDao: MovieDao) : AsyncTask<FavoriteMovie, Unit, Unit>() {
            val MovieDao = movieDao

            override fun doInBackground(vararg p0: FavoriteMovie?) {
                val movieList = MovieDao.getAllMovies()
            }
        }
    }
}