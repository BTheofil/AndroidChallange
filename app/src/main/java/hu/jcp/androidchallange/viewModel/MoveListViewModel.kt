package hu.jcp.androidchallange.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jcp.androidchallange.data.response.MovieList
import hu.jcp.androidchallange.repository.MovieRepository
import hu.jcp.androidchallange.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveListViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel(){

    private val _moveiesLiveData = MutableLiveData<Resource<MovieList>>()

    val moveiesLiveData : LiveData<Resource<MovieList>>
        get() = _moveiesLiveData

    init {
        getMoveies()
    }

    private fun getMoveies()  = viewModelScope.launch {
        _moveiesLiveData.postValue(Resource.loading(null))
        repository.getMoveList().let {
            if (it.isSuccessful){
                _moveiesLiveData.postValue(Resource.success(it.body()))
            }else{
                _moveiesLiveData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}