package hu.jcp.androidchallange.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.jcp.androidchallange.databinding.MovieItemBinding
import hu.jcp.androidchallange.model.Movie
import hu.jcp.androidchallange.util.ImageLoader
import javax.inject.Inject

class MovieAdapter @Inject constructor() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies : List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                movie.also { (title, vote, poster) ->
                    movieTitle.text = title
                    movieVote.text = vote
                }
                movie.poster?.let { ImageLoader.loadImage(itemView.context, it, moviePoster) }
            }
        }
    }
}