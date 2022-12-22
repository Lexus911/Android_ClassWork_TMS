package com.example.kotlinapp.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapp.utils.BundleConstants
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.FragmentItemsBinding
import com.example.kotlinapp.model.ItemsModel
import com.example.kotlinapp.presentation.adapter.ItemsAdapter
import com.example.kotlinapp.presentation.adapter.listener.ItemsListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// должна быть private, поэтому лучше в класс размещать или в компаньон обЪект, если надо использовать в другом фрагменте
//const val NAME = "name"

const val DETAILS = "Details"

@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemsListener, ItemsView {

    private var _binding: FragmentItemsBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemsAdapter: ItemsAdapter

   @Inject
    lateinit var itemsPresenter: ItemsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresenter.setView(this)

        itemsAdapter = ItemsAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = itemsAdapter

        itemsPresenter.getItems() // хочет получить лист
}

    override fun onClick() {
       itemsPresenter.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        itemsPresenter.itemClicked(name, date, imageView)
    }

    //мы можем это использовать, потому что мы видим откуда берётся константа
    companion object {
        const val DATE = "date"
        const val NAME = "name"
    }

    override fun itemsReceived(itemsList: List<ItemsModel>) {
        itemsAdapter.submitList(itemsList)
    }

    override fun imageViewClicked(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    override fun itemCLicked(navigationData: NavigateWithBundle) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putString(NAME, navigationData.name)
        bundle.putString(DATE, navigationData.date)
        bundle.putInt(BundleConstants.IMAGE_VIEW, navigationData.imageView)
        detailsFragment.arguments = bundle

        //.ADD мы не используем больше, используем .replace + .addToBackStack
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, detailsFragment)
            .addToBackStack(DETAILS)
            .commit()
    }
}

