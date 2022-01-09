package hu.jcp.androidchallange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import dagger.hilt.android.AndroidEntryPoint
import hu.jcp.androidchallange.databinding.ActivityMainBinding
import hu.jcp.androidchallange.databinding.FragmentMovieListBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    companion object{
        const val SORT_PREFERENCE = "SORT"
    }
}