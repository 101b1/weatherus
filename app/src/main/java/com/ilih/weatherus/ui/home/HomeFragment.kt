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
import com.ilih.weatherus.log
import javax.inject.Inject

const val NEW_CITY = "new_city"
const val TARGET_CITY = "target_city"
class HomeFragment : Fragment() {

    @Inject
    lateinit var homeViewModel: HomeViewModel
    lateinit var homeView: HomeView

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as MainActivity).getWeatherComponent().injectHomeFragment(this)
        log("Fragment created")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("View created")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        homeView = HomeViewImpl(
            binding,
            homeViewModel,
            this,
            (requireActivity() as MainActivity).getContextComponent().getContext()
        )
        val targetCity = arguments?.getLong(TARGET_CITY) ?: 0L
        homeView.onFinishInflate(homeViewModel.getListener(), targetCity)
        return view
    }

    override fun onStart() {
        if(arguments?.getBoolean(NEW_CITY) == true)
            homeView.refresh()
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}