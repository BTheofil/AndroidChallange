package hu.jcp.androidchallange

import hu.jcp.androidchallange.db.data.MovieDb
import hu.jcp.androidchallange.db.data.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : MovieDbRepository{
    private val movies = mutableListOf<MovieDb>()

    override suspend fun insertMovieDb(movieDb: MovieDb) {
        movies.add(movieDb)
    }

    override suspend fun deleteMovieDb(movieDb: MovieDb) {
        movies.remove(movieDb)
    }

    override suspend fun getMovieDbById(id: Int): MovieDb? {
        return movies.find { it.id == id }
    }

    override fun getMovieDbs(): Flow<List<MovieDb>> {
        return flow { emit(movies) }
    }
}