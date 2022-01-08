package hu.jcp.androidchallange.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hu.jcp.androidchallange.R
import hu.jcp.androidchallange.databinding.FragmentMovieListBinding
import hu.jcp.androidchallange.util.Status
import hu.jcp.androidchallange.view.adapter.MovieAdapter
import hu.jcp.androidchallange.viewModel.MoveListViewModel

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieAdapter: MovieAdapter
    private val movieViewModel : MoveListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUpObservers()
    }

    private fun initRecyclerView() {
        binding.movieListRV.apply {
            layoutManager = GridLayoutManager(context, 2)
            movieAdapter = MovieAdapter()
            adapter = movieAdapter
        }
    }

    private fun setUpObservers() {
        movieViewModel.moveiesLiveData.observe(viewLifecycleOwner, {
            when(it.status){
                Status.SUCCESS -> {
                    binding.circularProgressBar.visibility = View.GONE
                    binding.movieListRV.visibility = View.VISIBLE
                    movieAdapter.movieList = it.data!!.results
                    }

                Status.LOADING -> {
                    binding.circularProgressBar.visibility = View.VISIBLE
                    binding.movieListRV.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.circularProgressBar.visibility = View.GONE
                    binding.movieListRV.visibility = View.VISIBLE
                }
            }
        })
    }
}
