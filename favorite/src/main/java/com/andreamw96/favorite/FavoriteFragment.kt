package com.andreamw96.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.favorite.databinding.FragmentFavoriteBinding
import com.andreamw96.favorite.di.favoriteModule
import com.andreamw96.movieappcleanarchitecture.main.MovieAdapter
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by inject()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        loadKoinModules(favoriteModule)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val favMovieAdapter = MovieAdapter()
            binding.rvFavoriteMovie.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = favMovieAdapter
            }

            favoriteViewModel.favoriteMovieList.observe(viewLifecycleOwner, { data ->
                if (data != null) {
                    favMovieAdapter.submitList(data)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}