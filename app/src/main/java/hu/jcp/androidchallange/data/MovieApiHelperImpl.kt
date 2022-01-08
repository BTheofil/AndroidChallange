package hu.jcp.androidchallange.data

import hu.jcp.androidchallange.data.response.MovieList
import hu.jcp.androidchallange.util.Constant.API_KEY
import hu.jcp.androidchallange.util.Constant.LANGUAGE
import retrofit2.Response
import javax.inject.Inject

class MovieApiHelperImpl @Inject constructor(
    private val apiService: MovieApiService
):MovieApiHelper{
    override suspend fun getMovies(): Response<MovieList> = apiService.getMovieList(API_KEY, LANGUAGE)
}