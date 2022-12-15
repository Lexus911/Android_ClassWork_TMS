package com.example.kotlinapp.presentation.view.dataBinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.FragmentLoginBinding
import com.example.kotlinapp.presentation.view.OnBoardingFragment


class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.viewHandler = ViewHandler()
        binding.lifecycleOwner = viewLifecycleOwner
    }

    inner class ViewHandler{
        fun goToTheOnBoarding(){
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, OnBoardingFragment())
                .commit()
        }
    }


}