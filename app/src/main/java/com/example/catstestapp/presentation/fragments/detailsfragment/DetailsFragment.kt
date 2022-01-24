package com.example.catstestapp.presentation.fragments.detailsfragment

import android.Manifest
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.catstestapp.R
import com.example.catstestapp.databinding.FragmentDetailsBinding
import com.example.catstestapp.presentation.model.CatUI
import com.example.catstestapp.util.checkPermission
import com.example.catstestapp.util.isInternetAvailable
import com.example.catstestapp.util.loadImage
import com.example.catstestapp.util.saveImageToGallery
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val REQUEST_CODE = 111

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val binding: FragmentDetailsBinding by viewBinding()
    private val viewModel by viewModels<DetailsViewModel>()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = args.catUIItem.name
        setUIData()
    }

    private fun setUIData() {
        binding.apply {
            ivPic.loadImage(args.catUIItem.image?.url.toString())
            tvDesc.text = args.catUIItem.description
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_details, menu)
    }

    private fun collectFavorite(menu: Menu) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.checkFavorite(args.catUIItem)
            viewModel.isFavorite.collectLatest {
                if (it) {
                    menu.findItem(R.id.menu_delete_from_favorites).isVisible = true
                    menu.findItem(R.id.menu_add_to_favorites).isVisible = false
                } else {
                    menu.findItem(R.id.menu_delete_from_favorites).isVisible = false
                    menu.findItem(R.id.menu_add_to_favorites).isVisible = true
                }
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        collectFavorite(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                if (isInternetAvailable(requireContext()))
                    saveCatImage(args.catUIItem)
                else
                    Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT)
                        .show()
            }
            R.id.menu_add_to_favorites -> {
                viewModel.changeIsFavorite()
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
                viewModel.addToFavorites(args.catUIItem)
            }
            R.id.menu_delete_from_favorites -> {
                viewModel.changeIsFavorite()
                Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
                viewModel.deleteFromFavorites(args.catUIItem)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveCatImage(catItem: CatUI) {
        val bitmapDrawable: BitmapDrawable = binding.ivPic.drawable as BitmapDrawable
        val bitmap: Bitmap = bitmapDrawable.bitmap

        if (checkPermission(requireContext())) {
            saveImageToGallery(bitmap, catItem.id, requireContext().applicationContext)
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_CODE
            )
        }
    }
}

