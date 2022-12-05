package com.example.kotlinapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinapp.utils.BundleConstants.IMAGE_VIEW
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.FragmentDetailsBinding
import com.example.kotlinapp.databinding.FragmentItemsBinding
import com.example.kotlinapp.presentation.view.ItemsFragment.Companion.NAME


class DetailsFragment : Fragment() {
    private var _viewBinding: FragmentDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let {safeBundle ->
            val name = bundle.getString(NAME)
            val date = bundle.getString(ItemsFragment.Companion.DATE)
            val image = bundle.getInt(IMAGE_VIEW)

            viewBinding.detailsName.text = name
            viewBinding.detailsDate.text = date
            viewBinding.detailsImage.setBackgroundResource(image)
        }
    }
}