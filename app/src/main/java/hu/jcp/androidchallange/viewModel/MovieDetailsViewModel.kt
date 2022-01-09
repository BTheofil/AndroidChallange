package hu.jcp.androidchallange.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jcp.androidchallange.data.response.Result
import hu.jcp.androidchallange.db.data.MovieDbRepository
import hu.jcp.androidchallange.util.Converter.resultMovieToDb
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieDbRepository) : ViewModel() {

    fun addFav(movie: Result) {
        viewModelScope.launch {
            repository.insertMovieDb(resultMovieToDb(movie))
        }
    }
}