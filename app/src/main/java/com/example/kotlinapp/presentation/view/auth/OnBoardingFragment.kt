package com.example.kotlinapp.presentation.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.FragmentOnBoardingBinding
import com.example.kotlinapp.presentation.view.home.ItemsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : Fragment(), OnBoardingView {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var onBoardingPresenter: OnBoardingPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBoardingPresenter.setView(this) // инициализация вью

        binding.btnFinish.setOnClickListener {
            onBoardingPresenter.goToItemsFragment() // говорим презентеру идти в айтемс
        }
    }

    override fun goToItemsFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, ItemsFragment())
            .commit()
    }


}
