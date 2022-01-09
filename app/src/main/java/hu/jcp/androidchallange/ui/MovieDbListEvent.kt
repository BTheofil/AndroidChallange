package hu.jcp.androidchallange.ui

sealed class MovieDbListEvent {
    object OnAddMovieDbClick : MovieDbListEvent()
}