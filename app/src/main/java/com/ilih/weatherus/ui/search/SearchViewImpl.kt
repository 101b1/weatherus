package com.ilih.weatherus.ui.search

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilih.weatherus.R
import com.ilih.weatherus.databinding.FragmentSearchBinding
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.usecase.search.EmptySearch
import com.ilih.weatherus.domain.usecase.search.SearchError
import com.ilih.weatherus.domain.usecase.search.SearchLoading
import com.ilih.weatherus.domain.usecase.search.SearchResult

class SearchViewImpl(
    private val binding: FragmentSearchBinding,
    private val viewModel: SearchViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
): SearchView, CityItemAdapter.Listener {

    lateinit var searchViewListener: SearchView.Listener

    override fun onFinishInflate(listener: SearchView.Listener) {
        searchViewListener = listener
        initViews()
        setViewsListeners()
    }

    private fun setViewsListeners() {
    }

    fun initViews(){
        binding.recyclerCitySearch.adapter = CityItemAdapter(ArrayList(), this)
        binding.recyclerCitySearch.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModel.searchData().observe(lifecycleOwner){
            when(it){
                is SearchLoading ->{
                    binding.progressSearch.visibility = View.VISIBLE
                    binding.textNoSearchResult.visibility = View.GONE
                    binding.recyclerCitySearch.visibility = View.GONE
                }
                is SearchResult ->{
                    binding.progressSearch.visibility = View.GONE
                    binding.textNoSearchResult.visibility = View.GONE
                    binding.recyclerCitySearch.visibility = View.VISIBLE
                    (binding.recyclerCitySearch.adapter as CityItemAdapter).updateData(it.list)
                }
                is EmptySearch -> {
                    binding.progressSearch.visibility = View.GONE
                    binding.textNoSearchResult.visibility = View.VISIBLE
                    binding.recyclerCitySearch.visibility = View.GONE
                }
                is SearchError ->{
                    binding.progressSearch.visibility = View.GONE
                    binding.textNoSearchResult.visibility = View.VISIBLE
                    binding.recyclerCitySearch.visibility = View.GONE
                    Toast.makeText(context, context.getString(R.string.search_error), Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun onCityClicked(city: CityDto) {
        searchViewListener.onCityClicked(city)
    }
}