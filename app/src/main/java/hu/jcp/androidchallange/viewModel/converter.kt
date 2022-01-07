package hu.jcp.androidchallange.viewModel
import hu.jcp.androidchallange.data.response.Result
import hu.jcp.androidchallange.model.Movie

object Converter {

    fun apiMovieToModel(apiMovie: Result) : Movie =
        Movie(
            title = apiMovie.title,
            description = apiMovie.overview,
            vote = apiMovie.vote_count,
            poster = apiMovie.poster_path
        )
}