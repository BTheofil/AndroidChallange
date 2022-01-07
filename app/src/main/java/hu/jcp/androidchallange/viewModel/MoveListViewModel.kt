package hu.jcp.androidchallange.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jcp.androidchallange.model.Movie
import hu.jcp.androidchallange.viewModel.Converter.apiMovieToModel
import hu.jcp.androidchallange.repository.MovieRepository
import hu.jcp.androidchallange.util.Constant.API_KEY
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveListViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel(){

    private val moveiesLiveData = MutableLiveData<List<Movie>?>()

    fun getMoveies() = moveiesLiveData

    init {
        loadMoveies()
    }

    private fun loadMoveies(){
        viewModelScope.launch {
            val moveies = repository.getMoveList(API_KEY)
            if(moveies.data != null){
                val tempList = ArrayList<Movie>()
                    moveies.data.results.forEach{apiMovie ->
                        tempList.add(apiMovieToModel(apiMovie))
                    }
            moveiesLiveData.postValue(tempList)
            }
        }
    }

}