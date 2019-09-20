package com.lambdaschool.datapersistencesprintchallenge

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

class MovieRepo(context: Context) {
    private var movieDao: MovieDao
    lateinit var movieList: List<FavoriteMovie>


    init {
        val database: MovieDatabase = MovieDatabase.getInstance(context)!!
        movieDao = database.movieDao()
        //movieList = movieDao.getAllMovies()
    }

    //private var movieList: LiveData<List<FavoriteMovie>> = movieDao.getAllMovies()

    fun insert(movie: FavoriteMovie) {
        InsertMovieAsyncTask(movieDao).execute(movie)
    }

    fun update(movie: FavoriteMovie) {
        UpdateMovieAsyncTask(movieDao).execute(movie)
    }

    fun delete(movie: FavoriteMovie) {
        DeleteMovieAsyncTask(movieDao).execute(movie)
    }

    fun getAllMovies(): List<FavoriteMovie> {
        return AllPokemontMovieAsyncTask(movieDao).execute().get()
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

        private class AllPokemontMovieAsyncTask(movieDao: MovieDao) : AsyncTask<FavoriteMovie, Unit, List<FavoriteMovie>>() {
            val MovieDao = movieDao

            override fun doInBackground(vararg p0: FavoriteMovie?): List<FavoriteMovie> {
                val movieList = MovieDao.getAllMovies()
                return movieList
            }

            override fun onPostExecute(result: List<FavoriteMovie>?) {
                super.onPostExecute(result)
                val movieList = result

            }
        }
    }
}