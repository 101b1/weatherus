package com.ilih.weatherus.ui.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilih.weatherus.MainActivity
import com.ilih.weatherus.R
import com.ilih.weatherus.databinding.FragmentFavouritesBinding
import javax.inject.Inject

class FavouritesFragment : Fragment() {

    @Inject
    lateinit var viewModel: FavouritesViewModel
    lateinit var favouritesView: FavouritesView

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as MainActivity).getWeatherComponent().injectFavouritesFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val view = binding.root
        favouritesView = FavouritesViewImpl(
            binding,
            viewModel,
            this,
            (requireActivity() as MainActivity).getContextComponent().getContext()
        )
        favouritesView.onFinishInflate(viewModel.getViewListener())
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}