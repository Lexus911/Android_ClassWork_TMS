package com.example.kotlinapp.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.kotlinapp.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinapp.presentation.view.home.ItemsFragment.Companion.NAME
import com.example.kotlinapp.R
import com.example.kotlinapp.presentation.view.auth.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {


    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsName = view.findViewById<TextView>(R.id.detailsName)
        val detailsDate = view.findViewById<TextView>(R.id.detailsDate)
        val detailsImage = view.findViewById<ImageView>(R.id.detailsImage)

        val bundle = arguments
        bundle?.let { safeBundle ->
            val name = bundle.getString(NAME)
            val date = bundle.getString(ItemsFragment.DATE)
            val image = bundle.getInt(IMAGE_VIEW)

            detailsName.text = name
            detailsDate.text = date
            detailsImage.setBackgroundResource(image)
       }

        val btn = view.findViewById<Button>(R.id.btn_logout)
        btn.setOnClickListener{
            viewModel.logoutUser()
        }

        viewModel.nav.observe(viewLifecycleOwner){
            parentFragmentManager.beginTransaction()
                .replace(R.id.activity_container, LoginFragment())
                .commit()
        }



    }
}