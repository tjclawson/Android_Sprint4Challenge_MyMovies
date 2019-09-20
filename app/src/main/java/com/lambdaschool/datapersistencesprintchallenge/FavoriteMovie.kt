package com.lambdaschool.datapersistencesprintchallenge

import com.google.gson.annotations.SerializedName

class FavoriteMovie (
    @SerializedName("original_title")
    var title: String,
    @SerializedName("release_date")
    var date: String
)