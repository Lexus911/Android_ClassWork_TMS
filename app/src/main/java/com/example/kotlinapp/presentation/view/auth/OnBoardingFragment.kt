package com.example.kotlinapp.presentation.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinapp.databinding.FragmentOnBoardingBinding
import com.example.kotlinapp.utils.NavHelper.navigateWithDeletedBackStack


class OnBoardingFragment : Fragment() {
    private val viewModel: OnBoardingViewModel by viewModels()

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.nav.observe(viewLifecycleOwner){
                if(it!=null) {
                    navigateWithDeletedBackStack(it.destinationId, it.removeFragmentId)
                    viewModel.finishPerformed()
                }
            }

        }

    }
