package hu.jcp.androidchallange.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.jcp.androidchallange.data.response.MovieList
import hu.jcp.androidchallange.db.data.MovieDbRepository
import hu.jcp.androidchallange.repository.MovieRepository
import hu.jcp.androidchallange.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveListViewModel @Inject constructor(private val repository: MovieRepository, private val repositoryDb: MovieDbRepository) : ViewModel(){

    private val _moveiesLiveData = MutableLiveData<Resource<MovieList>>()

    val moveiesLiveData : MutableLiveData<Resource<MovieList>>
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

    fun sortMovies(mode : Int) {
        when(mode){
            0 -> sortRate()
            1 -> sortHot()
            2 -> showFav()
        }
    }

    private fun sortRate() {
        viewModelScope.launch{
            try {
                val movies : Resource<MovieList> = moveiesLiveData.value!!
                movies.data!!.results.sortedWith(compareBy { it.vote_average })
                moveiesLiveData.postValue(movies)
            }catch (e: Exception){
                return@launch
            }
        }
    }

    private fun sortHot(){
        viewModelScope.launch{
            try {
                val movies : Resource<MovieList> = moveiesLiveData.value!!
                movies.data!!.results.sortedWith(compareBy { it.popularity })
                moveiesLiveData.postValue(movies)
            }catch (e: Exception){
                return@launch
            }
        }
    }

    private fun showFav(){
        repositoryDb.getMovieDbs()
    }

}