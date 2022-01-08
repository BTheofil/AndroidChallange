package hu.jcp.androidchallange.data

import hu.jcp.androidchallange.data.response.MovieList
import retrofit2.Response

interface MovieApiHelper {
    suspend fun getMovies(): Response<MovieList>
}