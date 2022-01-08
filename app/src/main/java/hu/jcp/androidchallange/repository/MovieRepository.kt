package hu.jcp.androidchallange.repository

import hu.jcp.androidchallange.data.MovieApiHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiHelper: MovieApiHelper){

    suspend fun getMoveList() = apiHelper.getMovies()
}