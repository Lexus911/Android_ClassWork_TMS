package com.example.kotlinapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.utils.BundleConstants
import com.example.kotlinapp.R
import com.example.kotlinapp.data.ItemsRepositoryImpl
import com.example.kotlinapp.databinding.FragmentItemsBinding
import com.example.kotlinapp.databinding.FragmentOnBoardingBinding
import com.example.kotlinapp.domain.ItemsInteractor
import com.example.kotlinapp.presentation.adapter.ItemsAdapter
import com.example.kotlinapp.presentation.adapter.listener.ItemsListener
import com.example.kotlinapp.model.ItemsModel
import com.example.kotlinapp.presentation.ItemsPresenter
import com.example.kotlinapp.presentation.ItemsView

// должна быть private, поэтому лучше в класс размещать или в компаньон обЪект, если надо использовать в другом фрагменте
//const val NAME = "name"

const val DETAILS = "Details"

class ItemsFragment : Fragment(), ItemsListener, ItemsView {
    private var _viewBinding: FragmentItemsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var itemsAdapter: ItemsAdapter

    private lateinit var itemsPresenter: ItemsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentItemsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresenter = ItemsPresenter(this, ItemsInteractor(ItemsRepositoryImpl()))

        itemsAdapter = ItemsAdapter(this)
        viewBinding.recyclerView.adapter = itemsAdapter

        itemsPresenter.getData()

}

    override fun onClick() {
        itemsPresenter.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        itemsPresenter.elementSelected(name,  date, imageView)
    }

    //мы можем это использовать, потому что мы видим откуда берётся константа
    companion object {
        const val DATE = "date"
        const val NAME = "name"
    }

    override fun dataReceived(list: List<ItemsModel>) {
       itemsAdapter.submitList(list)
    }

    override fun imageViewClicked(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    override fun goToDetails(name: String, date: String, imageView: Int) {

            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(NAME, name)
            bundle.putString(DATE, date)
            bundle.putInt(BundleConstants.IMAGE_VIEW, imageView)
            detailsFragment.arguments = bundle

            //.ADD мы не используем больше, используем .replace + .addToBackStack
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, detailsFragment)
                .addToBackStack(DETAILS)
                .commit()
    }
}

