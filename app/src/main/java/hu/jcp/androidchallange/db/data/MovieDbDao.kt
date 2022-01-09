package hu.jcp.androidchallange.db.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDb(movieDb: MovieDb)

    @Delete
    suspend fun deleteMovieDb(movieDb: MovieDb)

    @Query("SELECT * FROM movieDb WHERE id = :id")
    suspend fun getMovieDbById(id: Int): MovieDb?

    @Query("SELECT * FROM movieDb")
    fun getMovieDbs(): Flow<List<MovieDb>>
}