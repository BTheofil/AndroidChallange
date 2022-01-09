package hu.jcp.androidchallange.data.response

import java.io.Serializable

data class MovieList(
    val dates: Dates,
    val page: Int,
    var results: MutableList<Result>,
    val total_pages: Int,
    val total_results: Int
) : Serializable

