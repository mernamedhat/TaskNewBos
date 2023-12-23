package com.example.taskbos.ui.albums

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.taskbos.api.InternetConnection
import com.example.taskbos.databinding.FragmentAlbumsDetailsBinding
import com.example.taskbos.model.PhotosResponse
import com.example.taskbos.prefrence.Preferences
import com.example.taskbos.ui.profile.ProfileFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAlbumsDetailsBinding
    private lateinit var preferences: Preferences
    private lateinit var photosList: ArrayList<PhotosResponse>
    private lateinit var gridAlbumsAdapter: GridAlbumsAdapter
    val args: AlbumsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumsDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = Preferences(requireActivity())
        photosList = ArrayList()
        preferences.savealbumId(args.albumid)
        gridAlbumsAdapter = GridAlbumsAdapter(photosList)
        fetchPhotos(preferences.getalbumId())
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.container.setOnRefreshListener {
            binding.container.isRefreshing = false
            fetchPhotos(preferences.getalbumId())

        }


        binding.idGRV.adapter = gridAlbumsAdapter

        binding.idGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

            val action =
                AlbumsDetailsFragmentDirections.fragmentAlbumsDetailsToPhotoDetails(photosList[position].url)
            findNavController().navigate(action)
        }

    }

    private fun fetchPhotos(albumid: Int) {

        if (InternetConnection.isInternetConnected(requireContext())) {
            val viewModel = ViewModelProvider(this)[AlbumsDetailsViewModel::class.java]
            viewModel.getPhotos(albumid).observe(requireActivity()) {
                photosList.clear()
                binding.progressBar.visibility = View.GONE
                photosList.addAll(it.body()!!)
                gridAlbumsAdapter.refreshList(photosList)
            }
        } else {
            Toast.makeText(
                requireContext(), " Check Internet Connection",
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    private fun filterList(query: String) {

        if (InternetConnection.isInternetConnected(requireContext())) {
            val filteredList = photosList.filter { photo ->
                photo.title.contains(query.orEmpty(), ignoreCase = true)
            }
//            if (photosList.size != 0)
//                binding.progressBar.visibility = View.GONE
//            else
//                binding.progressBar.visibility = View.VISIBLE

            gridAlbumsAdapter.submitList(filteredList)
        }
    }


}


