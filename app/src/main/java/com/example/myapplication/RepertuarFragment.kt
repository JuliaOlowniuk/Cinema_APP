package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepertuarFragment : Fragment() {

    private lateinit var moviesLayout: LinearLayout
    private val apiKey = "b86b2f78b9fa1efd17365a41927922e1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repertuar, container, false)
        moviesLayout = view.findViewById(R.id.movies_layout)
        val buttonChooseMovie = view.findViewById<Button>(R.id.button_choose_movie)

        fetchAndDisplayMovies()

        buttonChooseMovie.setOnClickListener {
            val selectedMovies = getSelectedMovies()
            Toast.makeText(activity, "Selected movies: $selectedMovies", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_repertuarFragment_to_reservationFragment)
        }

        return view
    }

    private fun fetchAndDisplayMovies() {
        RetrofitInstance.api.getNowPlayingMovies(apiKey).enqueue(object : Callback<TMDbResponse> {
            override fun onResponse(call: Call<TMDbResponse>, response: Response<TMDbResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { movies ->
                        displayMovies(movies)
                    }
                }
            }

            override fun onFailure(call: Call<TMDbResponse>, t: Throwable) {
                Toast.makeText(activity, "Failed to fetch movies", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayMovies(movies: List<Movie>) {
        for (movie in movies) {
            val movieView = layoutInflater.inflate(R.layout.item_movie, moviesLayout, false)
            val checkBox = movieView.findViewById<CheckBox>(R.id.movie_checkbox)
            val imageView = movieView.findViewById<ImageView>(R.id.movie_image)
            val textView = movieView.findViewById<TextView>(R.id.movie_title)

            textView.text = movie.title
            Picasso.get().load(movie.posterUrl).into(imageView)

            moviesLayout.addView(movieView)
        }
    }

    private fun getSelectedMovies(): List<String> {
        val selectedMovies = mutableListOf<String>()
        for (i in 0 until moviesLayout.childCount) {
            val movieView = moviesLayout.getChildAt(i)
            val checkBox = movieView.findViewById<CheckBox>(R.id.movie_checkbox)
            if (checkBox.isChecked) {
                val textView = movieView.findViewById<TextView>(R.id.movie_title)
                selectedMovies.add(textView.text.toString())
            }
        }
        return selectedMovies
    }
}