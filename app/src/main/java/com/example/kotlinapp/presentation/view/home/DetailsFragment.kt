package com.example.kotlinapp.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinapp.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinapp.presentation.view.home.ItemsFragment.Companion.NAME
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.FragmentDetailsBinding
import com.example.kotlinapp.presentation.view.auth.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment(), DetailsView {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsPresenter.setView(this)

        val bundle = arguments
        bundle?.let { safeBundle ->
            detailsPresenter.getArguments(
                bundle.getString(NAME),
                bundle.getString(ItemsFragment.DATE),
                bundle.getInt(IMAGE_VIEW)
            )
       }

        binding.btnLogout.setOnClickListener{
            detailsPresenter.logoutUser()
        }
    }

    override fun userLoggedOut() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_container, LoginFragment())
            .commit()
    }

    override fun displayItemData(name: String, date: String, imageView: Int) {
        binding.detailsName.text = name
        binding.detailsDate.text = date
        binding.detailsImage.setBackgroundResource(imageView)
    }
}