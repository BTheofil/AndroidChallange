package hu.jcp.androidchallange.db.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieDb::class],
    version = 1
)
abstract class MovieDbDatabase: RoomDatabase() {

    abstract val dao: MovieDbDao
}