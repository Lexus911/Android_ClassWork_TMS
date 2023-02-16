package com.example.kotlinapp.presentation.view.home.items

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.App
import com.example.kotlinapp.R
import com.example.kotlinapp.presentation.adapter.ItemsAdapter
import com.example.kotlinapp.presentation.adapter.listener.ItemsListener
import com.example.kotlinapp.utils.BaseFragment
import com.example.kotlinapp.utils.BundleConstants
import com.example.kotlinapp.utils.NavHelper.navigateWithBundle
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

// должна быть private, поэтому лучше в класс размещать или в компаньон обЪект, если надо использовать в другом фрагменте
//const val NAME = "name"


class ItemsFragment : BaseFragment(), ItemsListener {
    private lateinit var itemsAdapter: ItemsAdapter
    private val viewModel: ItemsViewModel by viewModels{viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

        Handler().postDelayed({
            navigateWithBundle(
                R.id.action_itemsFragment_to_detailsFragment,
                bundleOf()
            )
        }, 2000)

        val h: Handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                if (msg.what === 0) {
                    Log.w("ui can be updated", "called")
                } else {
                    Log.w("error", "shown")
                }
            }
        }

        val t: Thread = object : Thread() {
            override fun run() {
                Log.w("heavy work", "executing")
                if (false) { //true
                    //we can't update the UI from here so we'll signal our handler and it will do it for us.
                    h.sendEmptyMessage(0)
                } else {
                    h.sendEmptyMessage(1)
                }
            }
        }

        t.start()






        itemsAdapter = ItemsAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemsAdapter

//1 способ
//        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//            viewModel.getData.collect()
//        }

// 2 способ
//        viewModel.getData()
//        viewModel.trigger.observe(viewLifecycleOwner){
//            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//                it.collect()
//            }
//        }

       //3 способ
       viewLifecycleOwner.lifecycleScope.launchWhenResumed {
           viewModel.getDataSimple()
       }

//        viewModel.items.observe(viewLifecycleOwner){ listItems ->
//            itemsAdapter.submitList(listItems)
//        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.items.catch {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
                .collect{ flowList ->
                flowList.collect{ list ->
                    itemsAdapter.submitList(list)
                }
            }
        }

        viewModel.msg.observe(viewLifecycleOwner){ msg ->
            Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner){ navBundle ->

            if(navBundle != null){
            val bundle = Bundle()

            bundle.putString(DESCRIPTION, navBundle.description)
            bundle.putString(BundleConstants.IMAGE_VIEW, navBundle.image)

                navigateWithBundle(navBundle.destinationId, bundle)

            viewModel.userNavigated() // в конце навигации говрим вьюмодели что действие выполнено
        }
    }
}

    override fun onClick() {

        viewModel.imageViewCLicked()
    }

    override fun onElementSelected(description: String, image: String) {

        viewModel.elementClicked(description, image)
    }

    override fun onDeleteClicked(description: String) {
        viewModel.deleteItem(description)
    }

    override fun onFavClicked(description: String) {
        viewModel.onFavClicked(description)
    }


    //мы можем это использовать, потому что мы видим откуда берётся константа
    companion object {
        const val DESCRIPTION = "description"
    }

}

