package com.example.kotlinapp.presentation.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.FragmentHomeBinding
import com.example.kotlinapp.presentation.view.auth.OnBoardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showUserData()

        binding.btnFinish.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.activity_container,OnBoardingFragment())
                .commit()
        }

        viewModel.userCreds.observe(viewLifecycleOwner){
            binding.tvUserCreds.text = "${it.userName} \n${it.userPassword}"
        }



    }
}