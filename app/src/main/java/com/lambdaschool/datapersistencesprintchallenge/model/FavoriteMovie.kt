package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
class FavoriteMovie (
    var title: String,
    var date: String,
    var watched: Boolean,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)