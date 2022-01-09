package hu.jcp.androidchallange.util
import hu.jcp.androidchallange.data.response.Result
import hu.jcp.androidchallange.db.data.MovieDb
import hu.jcp.androidchallange.model.Movie

object Converter {

    fun apiMovieToModel(apiMovie: Result) : Movie =
        Movie(
            title = apiMovie.title,
            description = apiMovie.overview,
            vote = apiMovie.vote_count,
            poster = apiMovie.poster_path
        )

    fun resultMovieToDb(result: Result) : MovieDb =
        MovieDb(
            title = result.title,
            description = result.overview,
            vote = result.vote_average.toInt(),
            poster = result.poster_path
        )
}