package com.ilih.weatherus.ui.favourites

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilih.weatherus.databinding.FragmentFavouritesBinding
import com.ilih.weatherus.databinding.FragmentHomeBinding
import com.ilih.weatherus.domain.entity.CityDto
import com.ilih.weatherus.domain.usecase.favourites.EmptyFavourites
import com.ilih.weatherus.domain.usecase.favourites.FavouriteResult
import com.ilih.weatherus.domain.usecase.favourites.FavouritesError
import com.ilih.weatherus.ui.home.HomeViewModel
import com.ilih.weatherus.ui.search.CityItemAdapter

class FavouritesViewImpl(
    private val binding: FragmentFavouritesBinding,
    private val viewModel: FavouritesViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : FavouritesView, CityItemAdapter.Listener, CityItemAdapter.DeleteListener {

    private var listener: FavouritesView.Listener? = null

    override fun onFinishInflate(listener: FavouritesView.Listener) {
        this.listener = listener
        initViews()
        listener.inflated()
    }

    fun initViews() {
        binding.recyclerFavourites.adapter = CityItemAdapter(ArrayList(), this, this, "favourites")
        binding.recyclerFavourites.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModel.getData().observe(lifecycleOwner) {
            when(it){
                is FavouriteResult -> {
                    binding.textNoFavourites.visibility = View.GONE
                    (binding.recyclerFavourites.adapter as CityItemAdapter).updateData(it.list)
                }
                is EmptyFavourites -> {
                    binding.textNoFavourites.visibility = View.VISIBLE
                }
                is FavouritesError -> {
                    Toast.makeText(context, "Favourites error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCityClicked(city: CityDto) {
        listener?.onFavouriteClicked(city)
    }

    override fun onDeleteClicked(city: CityDto) {
        TODO("Not yet implemented")
    }
}