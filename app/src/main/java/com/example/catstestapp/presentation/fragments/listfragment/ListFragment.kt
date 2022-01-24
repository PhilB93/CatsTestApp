package com.example.catstestapp.presentation.fragments.listfragment


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.catstestapp.R
import com.example.catstestapp.databinding.ListFragmentBinding
import com.example.catstestapp.presentation.model.CatUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.list_fragment), ListOfCatsListener {

    private val viewModel by viewModels<ListViewModel>()
    private val binding: ListFragmentBinding by viewBinding()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { CatAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Cats ^^"
        setRecycler()
        collectData()
        handleUIStates()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchCats().collectLatest { pagedData ->
                adapter.submitData(pagedData)
            }
        }
    }

    private fun setRecycler() {
        binding.apply {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, 1)

            recyclerView.adapter = adapter.withLoadStateFooter(
                footer = LoadStateAdapter(adapter)
            )
        }
    }

    private fun handleUIStates() {
        adapter.addLoadStateListener { state ->
            with(binding) {
                recyclerView.isVisible = state.refresh != LoadState.Loading
                progressBar.isVisible = state.refresh == LoadState.Loading
            }
        }
        binding.buttonRetry.setOnClickListener {
            adapter.retry()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorites) {
            findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToFavoritesFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(cat: CatUI) {
        findNavController()
            .navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(cat))
    }
}
