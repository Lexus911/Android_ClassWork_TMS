package com.example.kotlinapp.presentation.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.App
import com.example.kotlinapp.databinding.FragmentLoginBinding
import com.example.kotlinapp.utils.BaseFragment
import com.example.kotlinapp.utils.NavHelper.navigate
import javax.inject.Inject


class LoginFragment : BaseFragment() {
    private val viewModel: LoginViewModel by viewModels{ viewModelFactory }

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
        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

        binding.btnShowCreds.setOnClickListener{
        viewModel.loginUser(
            binding.etText.text.toString(),
            binding.etText2.text.toString()
        )
    }
        viewModel.nav.observe(viewLifecycleOwner){
            if(it != null){
                navigate(it)
            viewModel.userNavigated()
        }
        }
    }
}