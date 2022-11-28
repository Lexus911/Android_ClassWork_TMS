package com.example.kotlinapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.adapter.ItemsAdapter
import com.example.kotlinapp.listener.ItemsListener
import com.example.kotlinapp.model.ItemsModel

class ItemsFragment : Fragment(), ItemsListener {

    private lateinit var itemsAdapter: ItemsAdapter

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

        val listItems = mutableListOf<ItemsModel>(
            ItemsModel(R.drawable.dragon_fruit_max, "Android", "04.11.2023"),
            ItemsModel(R.drawable.blue_banana, "IOS", "05.10.2023"),
            ItemsModel(R.drawable.fruit_1, "C#", "06.09.2023"),
            ItemsModel(R.drawable.lichi, "C++", "07.08.2023"),
            ItemsModel(R.drawable.avokado, "C", "08.07.2023"),
            ItemsModel(R.drawable.ic_launcher_background, "Ruby", "09.06.2023"),
            ItemsModel(R.drawable.ic_launcher_foreground, "Flutter", "10.05.2023"),
            ItemsModel(R.drawable.avokado, "Java", "11.04.2023"),
            ItemsModel(R.drawable.blue_banana, "JavaScript", "12.03.2023"),
            ItemsModel(R.drawable.avokado, "IOS", "13.02.2023"),
            ItemsModel(R.drawable.dragon_fruit_max, "Android", "14.01.2023")
        )

        itemsAdapter.submitList(listItems)
    }

    override fun onClick() {
    Toast.makeText(context, "ImageViewClicked", Toast.LENGTH_SHORT).show()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("date", date)
        bundle.putInt("imageView", imageView)
        detailsFragment.arguments = bundle

        //.ADD мы не используем больше, используем .replce + .addToBackStack
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, detailsFragment)
            .addToBackStack("Details")
            .commit()

    }
}