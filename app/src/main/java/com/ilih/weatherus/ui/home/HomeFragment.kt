package com.ilih.weatherus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ilih.weatherus.App
import com.ilih.weatherus.MainActivity
import com.ilih.weatherus.R
import com.ilih.weatherus.databinding.FragmentHomeBinding
import com.ilih.weatherus.di.WeatherComponent
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeViewModel: HomeViewModel
    lateinit var homeView: HomeView

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        WeatherComponent.create((requireActivity().application as App).getAppComponent())
            .injectHomeFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        homeView = HomeViewImpl(
            binding,
            homeViewModel,
            this,
            (requireActivity() as MainActivity).getContextComponent().getContext()
        )
        (homeView as HomeViewImpl).onFinishInflate(homeViewModel.getListener())
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}