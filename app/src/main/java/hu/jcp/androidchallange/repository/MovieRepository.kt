package hu.jcp.androidchallange.repository

import dagger.hilt.android.scopes.ActivityScoped
import hu.jcp.androidchallange.util.Resource
import hu.jcp.androidchallange.data.MovieApi
import hu.jcp.androidchallange.data.response.MovieList
import javax.inject.Inject

@ActivityScoped
class MovieRepository @Inject constructor(private val api: MovieApi){

    suspend fun getMoveList(apiKey : String) : Resource<MovieList> {
        val response = try {
            api.getMovieList(apiKey)
        } catch (e: Exception){
            return Resource.Error("An unknown error")
        }
        return Resource.Success(response)
    }
}