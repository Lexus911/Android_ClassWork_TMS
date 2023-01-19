package com.example.kotlinapp.presentation.view.home

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kotlinapp.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinapp.R
import com.squareup.picasso.Picasso
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

        val detailsDate = view.findViewById<TextView>(R.id.detailsDate)
        val detailsImage = view.findViewById<ImageView>(R.id.detailsImage)

        val bundle = arguments
        bundle?.let { safeBundle ->

            val description = safeBundle.getString("description")
            val image = safeBundle.getString(IMAGE_VIEW)


            detailsDate.text = description
            Picasso.get().load(Uri.parse(image)).into(detailsImage)
       }

        val btn = view.findViewById<Button>(R.id.btn_logout)
        btn.setOnClickListener{
            viewModel.logoutUser()
        }

        viewModel.nav.observe(viewLifecycleOwner){
            if(it!=null) {
                val navGraph = findNavController().navInflater.inflate(it)
                navGraph.startDestination = R.id.loginFragment
                findNavController().graph = navGraph
            }
        }
    }
}