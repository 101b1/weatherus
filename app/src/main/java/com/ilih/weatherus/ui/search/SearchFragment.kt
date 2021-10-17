package com.ilih.weatherus.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ilih.weatherus.MainActivity
import com.ilih.weatherus.databinding.FragmentSearchBinding
import io.reactivex.internal.operators.maybe.MaybeDoOnTerminate
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModel: SearchViewModel
    lateinit var searchView: SearchView

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as MainActivity).getWeatherComponent().injectSearchFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        searchView = SearchViewImpl(
            binding,
            viewModel,
            this,
            (requireActivity() as MainActivity).getContextComponent().getContext()
        )
        viewModel.searchData()
        searchView.onFinishInflate(viewModel.getViewListener())
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}