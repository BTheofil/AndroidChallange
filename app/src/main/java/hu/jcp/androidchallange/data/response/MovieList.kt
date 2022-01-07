package hu.jcp.androidchallange.data.response

data class MovieList(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)