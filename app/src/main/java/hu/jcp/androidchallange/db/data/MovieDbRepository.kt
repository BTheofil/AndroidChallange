package hu.jcp.androidchallange.db.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

interface MovieDbRepository {

    suspend fun insertMovieDb(movieDb: MovieDb)

    suspend fun deleteMovieDb(movieDb: MovieDb)

    suspend fun getMovieDbById(id: Int): MovieDb?

    fun getMovieDbs(): Flow<List<MovieDb>>
}