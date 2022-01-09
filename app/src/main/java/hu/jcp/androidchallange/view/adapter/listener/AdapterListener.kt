package hu.jcp.androidchallange.view.adapter.listener
import hu.jcp.androidchallange.data.response.Result

interface AdapterListener {
    fun onClickItem(movie: Result)
}