package hu.jcp.androidchallange.data

import hu.jcp.androidchallange.data.response.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/movie/now_playing")
    suspend fun getMovieList(@Query("api_key") apiKey : String,) : MovieList
}