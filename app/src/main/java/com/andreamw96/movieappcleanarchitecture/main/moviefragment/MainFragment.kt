package com.andreamw96.movieappcleanarchitecture.main.moviefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.core.data.Resource
import com.andreamw96.movieappcleanarchitecture.databinding.FragmentMainBinding
import com.andreamw96.movieappcleanarchitecture.detailmovie.DetailMovieActivity
import com.andreamw96.movieappcleanarchitecture.main.MovieAdapter
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by inject()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            binding.rvMovie.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = movieAdapter
            }

            mainViewModel.movieList.observe(viewLifecycleOwner, { data ->
                if (data != null) {
                    when(data) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(data.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(activity, data.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            movieAdapter.onItemMovieClick = { selectedMovie ->
                startActivity(
                    Intent(activity, DetailMovieActivity::class.java)
                        .putExtra(DetailMovieActivity.EXTRA_DATA, selectedMovie)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}