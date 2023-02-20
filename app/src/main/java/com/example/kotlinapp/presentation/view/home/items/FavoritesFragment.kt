package com.example.kotlinapp.presentation.view.home.items

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapp.App
import com.example.kotlinapp.databinding.FragmentFavoritesBinding
import com.example.kotlinapp.presentation.receiver.AirplaneModeChangeReceiver
import com.example.kotlinapp.presentation.receiver.MyBroadcastReceiver
import com.example.kotlinapp.presentation.view.home.items.adapter.FavoritesAdapter
import com.example.kotlinapp.utils.BaseFragment
import javax.inject.Inject


class FavoritesFragment : BaseFragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favAdapter: FavoritesAdapter
    private val viewModel: FavoritesViewModel by viewModels{viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

        val receiver = AirplaneModeChangeReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            requireContext().registerReceiver(receiver, it)
        }

        val receiver2 = MyBroadcastReceiver()
        IntentFilter("MY_ACTION").also {
            requireContext().registerReceiver(receiver2, it)
        }
        setMessage()


        favAdapter = FavoritesAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = favAdapter


        viewModel.getFavorites()

        viewModel.favorites.observe(viewLifecycleOwner){
            favAdapter.submitList(it)
        }
    }
    private fun setMessage(){
        val intent = Intent("MY_ACTION")
        intent.putExtra("KEY", "message")
        requireContext().sendBroadcast(intent)
    }

}
