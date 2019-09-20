package com.lambdaschool.datapersistencesprintchallenge

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

@Database(entities =[FavoriteMovie::class], exportSchema = true,version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (instance == null) {
                synchronized(MovieDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java, "movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance as MovieDatabase
        }

        fun destroyInstance() {
            instance = null
        }
    }

}