package hu.jcp.androidchallange.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import hu.jcp.androidchallange.data.response.Result
import hu.jcp.androidchallange.databinding.FragmentMovieDetailsBinding
import hu.jcp.androidchallange.util.ImageLoader
import hu.jcp.androidchallange.viewModel.MovieDetailsViewModel

class MovieDetailsFragment : Fragment() {

    var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val movieDetailsViewModel : MovieDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val selectedMovie = arguments?.get ("movie") as Result

        binding.header.movieTitleDetails.text = selectedMovie.title
        binding.movieDescriptionDetails.text = selectedMovie.overview
        binding.header.movieSmallDescDetails.text = selectedMovie.release_date
        ImageLoader.loadImage(requireContext(), selectedMovie.poster_path, binding.movieImageDetail)

        binding.backArrowHeader.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.addFavBtn.setOnClickListener {
            movieDetailsViewModel.addFav(selectedMovie)
        }
    }

}