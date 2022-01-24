package com.example.catstestapp.presentation.fragments.favoritesfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.catstestapp.R
import com.example.catstestapp.databinding.FragmentFavoritesBinding
import com.example.catstestapp.presentation.fragments.listfragment.ListOfCatsListener
import com.example.catstestapp.presentation.model.CatUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites), ListOfCatsListener {
    private val viewModel by viewModels<FavoritesViewModel>()
    private val binding: FragmentFavoritesBinding by viewBinding()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { FavoritesAdapter(this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Favorites"
        setRecycler()
        collectData()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getList().collectLatest {
                adapter.submitList(it)
            }
        }
    }

    private fun setRecycler() {
        binding.apply {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, 1)
            recyclerView.adapter = adapter
        }
    }

    override fun onClick(cat: CatUI) {
        findNavController()
            .navigate(FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(cat))
    }
}