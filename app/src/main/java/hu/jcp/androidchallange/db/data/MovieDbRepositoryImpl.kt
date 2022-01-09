package hu.jcp.androidchallange.db.data

import kotlinx.coroutines.flow.Flow

class MovieDbRepositoryImpl(
    private val dao: MovieDbDao
): MovieDbRepository {

    override suspend fun insertMovieDb(movieDb: MovieDb) {
        dao.insertMovieDb(movieDb)
    }

    override suspend fun deleteMovieDb(movieDb: MovieDb) {
        dao.deleteMovieDb(movieDb)
    }

    override suspend fun getMovieDbById(id: Int): MovieDb? {
        return dao.getMovieDbById(id)
    }

    override fun getMovieDbs(): Flow<List<MovieDb>> {
        return dao.getMovieDbs()
    }
}