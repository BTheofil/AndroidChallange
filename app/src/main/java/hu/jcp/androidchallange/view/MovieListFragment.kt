package hu.jcp.androidchallange.view

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hu.jcp.androidchallange.MainActivity
import hu.jcp.androidchallange.R
import hu.jcp.androidchallange.data.response.Result
import hu.jcp.androidchallange.databinding.FragmentMovieListBinding
import hu.jcp.androidchallange.util.Status
import hu.jcp.androidchallange.view.adapter.MovieAdapter
import hu.jcp.androidchallange.view.adapter.listener.AdapterListener
import hu.jcp.androidchallange.viewModel.MoveListViewModel

@AndroidEntryPoint
class MovieListFragment : Fragment(), AdapterListener {

    var _binding: FragmentMovieListBinding ? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var navController: NavController
    private lateinit var preferences: SharedPreferences
    private val movieViewModel : MoveListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
        preferences = requireActivity().getSharedPreferences(
            MainActivity.SORT_PREFERENCE,
            Context.MODE_PRIVATE
        )
        initRecyclerView()
        setUpObservers()
        initMenuBtn()
    }

    override fun onClickItem(movie: Result) {
       val bundle = bundleOf("movie" to movie)
        navController.navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundle)
    }

    private fun initRecyclerView() {
        binding.movieListRV.apply {
            layoutManager = GridLayoutManager(context, 2)
            movieAdapter = MovieAdapter(this@MovieListFragment)
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

    private fun initMenuBtn(){
        val options = resources.getStringArray(R.array.sort)

        binding.toolbar.btMenu.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(R.string.orders)
                .setItems(options){ _, position ->
                    preferences.edit().putInt(MainActivity.SORT_PREFERENCE, position).apply()
                    movieViewModel.sortMovies(position)
                }.create().show()
        }
    }
}
