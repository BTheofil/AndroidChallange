package hu.jcp.androidchallange.db.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDb (
    val title : String,
    val description : String,
    val vote : Int,
    val poster : String?,
    @PrimaryKey val id: Int? = null
)