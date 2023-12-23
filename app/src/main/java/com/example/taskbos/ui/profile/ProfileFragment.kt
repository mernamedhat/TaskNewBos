package com.example.taskbos.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskbos.databinding.FragmentProfileBinding

import com.example.taskbos.api.InternetConnection

import com.example.taskbos.model.AlbumsResponse
import com.example.taskbos.prefrence.Preferences
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class ProfileFragment : Fragment(), AlbumAdapter.OnItemClickListener {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var preferences: Preferences
    private lateinit var albumsList: ArrayList<AlbumsResponse>
    private lateinit var albumsAdapter: AlbumAdapter
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = Preferences(requireActivity())
        albumsList = ArrayList()
        albumsAdapter = AlbumAdapter(albumsList, this@ProfileFragment)
        fetchData()
        fetchRec()
        binding.albumRec.layoutManager = LinearLayoutManager(requireContext())
        binding.albumRec.setHasFixedSize(true)

        binding.albumRec.adapter = albumsAdapter




    }

    private fun fetchData() {
        val randomInt = Random.nextInt(0, 10)
        if (InternetConnection.isInternetConnected(requireContext())) {
//            val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
            viewModel.getHome().observe(requireActivity()) {
                preferences.saveUserId(it.body()?.get(randomInt)?.id.toString())
                binding.name.text = it.body()?.get(randomInt)?.name.toString()
                binding.address.text = "${it.body()?.get(randomInt)?.address?.street.toString()},${
                    it.body()?.get(randomInt)?.address?.suite.toString()
                },${it.body()?.get(randomInt)?.address?.city.toString()},\n ${
                    it.body()?.get(randomInt)?.address?.zipcode.toString()
                },"
            }
        } else {

            Toast.makeText(
                requireContext(), " Check Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun fetchRec() {
        if (InternetConnection.isInternetConnected(requireContext())) {
//            val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
            viewModel.getAlbums(preferences.getUserId()!!.toInt()).observe(requireActivity()) {
                albumsList.clear()
                binding.progressBar.visibility=View.GONE
                albumsList.addAll(it.body()!!)
                albumsAdapter.notifyDataSetChanged()
            }
        } else {

            Toast.makeText(
                requireContext(), " Check Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onItemClick(position: Int, item: AlbumsResponse) {

        val action = ProfileFragmentDirections.fragmentProfileToAlbumsDetails(item.id.toInt())
        findNavController().navigate(action)
    }


}