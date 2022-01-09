package hu.jcp.androidchallange

import hu.jcp.androidchallange.data.response.Result
import hu.jcp.androidchallange.viewModel.MovieDetailsViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesTest {

    private lateinit var getMovies: MovieDetailsViewModel
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getMovies = MovieDetailsViewModel(fakeRepository)

//        val moviesToInsert = mutableListOf<MovieDb>()
//        ('a'..'z').forEachIndexed { index, c ->
//            moviesToInsert.add(
//                MovieDb(
//                    title = c.toString(),
//                    description = c.toString(),
//                    vote = index,
//                    poster = c.toString()
//                )
//            )
//        }
//        moviesToInsert.shuffle()
//        runBlocking {
//            moviesToInsert.forEach { fakeRepository.insertMovieDb(it) }
//        }
    }

    @Test
    fun `ViewModel correctly add element`() = runBlocking {
        getMovies.addFav(Result(adult = false, backdrop_path = "a", genre_ids = emptyList(), id = 1, original_language = "a", overview = "a",
        popularity = 1.0, poster_path = "a", release_date = "a", title = "a", video = false, vote_average = 1.0, vote_count = 1, original_title = "a"))

        fakeRepository.getMovieDbs().count()

        assertEquals(fakeRepository.getMovieDbs().count().toString(), 1.toString())

    }
}