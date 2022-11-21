package com.example.kotlinapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ListViewKotlinActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_kotlin)

        val listView = findViewById<ListView>(R.id.listView)
        val list = listOf<String>(
            "Apple",
            "Banana",
            "Kiwi",
            "Orange",
            "Pineapple",
            "Watermelon"
        )

        val adapter = ArrayAdapter(this, R.layout.fruit_item_layout, list)
        listView.adapter = adapter
    }


    companion object{
        fun listViewKotlinActivityStart(context: Context) {
            context.startActivity((Intent(context, ListViewKotlinActivity::class.java)))
        }
    }
}

