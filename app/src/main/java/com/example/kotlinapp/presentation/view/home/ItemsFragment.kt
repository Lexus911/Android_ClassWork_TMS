package com.example.kotlinapp.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.utils.BundleConstants
import com.example.kotlinapp.R
import com.example.kotlinapp.presentation.adapter.ItemsAdapter
import com.example.kotlinapp.presentation.adapter.listener.ItemsListener
import dagger.hilt.android.AndroidEntryPoint

// должна быть private, поэтому лучше в класс размещать или в компаньон обЪект, если надо использовать в другом фрагменте
//const val NAME = "name"

const val DETAILS = "Details"

@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemsListener {

    private lateinit var itemsAdapter: ItemsAdapter
    private val viewModel: ItemsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsAdapter = ItemsAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemsAdapter

        viewModel.getData()

        viewModel.items.observe(viewLifecycleOwner){ listItems ->
            itemsAdapter.submitList(listItems)
        }

        viewModel.msg.observe(viewLifecycleOwner){ msg ->
            Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner){ navBundle ->

            if(navBundle != null){
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(NAME, navBundle.name)
            bundle.putString(DATE, navBundle.date)
            bundle.putInt(BundleConstants.IMAGE_VIEW, navBundle.image)
            detailsFragment.arguments = bundle

            //.ADD мы не используем больше, используем .replace + .addToBackStack
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, detailsFragment)
                .addToBackStack(DETAILS)
                .commit()

            viewModel.userNavigated() // в конце навигации говрим вьюмодели что действие выполнено
        }
    }
}

    override fun onClick() {

        viewModel.imageViewCLicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {

        viewModel.elementClicked(name,  date, imageView)
    }


    //мы можем это использовать, потому что мы видим откуда берётся константа
    companion object {
        const val DATE = "date"
        const val NAME = "name"
    }

}

