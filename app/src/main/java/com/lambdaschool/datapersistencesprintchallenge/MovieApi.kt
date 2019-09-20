package com.lambdaschool.datapersistencesprintchallenge

import com.google.gson.Gson
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.API_KEY_PARAM
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.BASE_URL
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.SEARCH_MOVIES_ENDPOINT
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{

    @GET("search/movie${MovieConstants.FIXED_QUERY_PARAMS}")
    fun getMovies(@Query("query")query: String,
                  @Query("api_key") key: String): Call<MovieSearchResult>

    class Factory {

        companion object {
            val gson = Gson()

            fun create(): MovieApi {

                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(MovieApi::class.java)
            }
        }
    }
}