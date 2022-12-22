package com.example.kotlinapp.presentation.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.FragmentLoginBinding
import com.example.kotlinapp.presentation.view.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(), LoginView {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var loginPresenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginPresenter.setView(this)

        binding.btnShowCreds.setOnClickListener{
            loginPresenter.loginUser(
                binding.etText.text.toString(),
                binding.etText2.text.toString())
    }

        }

    override fun userLoggedIn() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_container, HomeFragment())
            .commit()
    }
}
