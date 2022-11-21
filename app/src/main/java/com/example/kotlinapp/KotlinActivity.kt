package com.example.kotlinapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.kotlinapp.ListViewKotlinActivity.Companion.listViewKotlinActivityStart
import com.example.kotlinapp.kotlin.HausBuilder

class KotlinActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)


        val btn = findViewById<Button>(R.id.btn5)
        val btn2 = findViewById<Button>(R.id.btn6)

        btn2.setOnClickListener{
            listViewKotlinActivityStart(this)
        }

        val person = object{           //анонимный класс
            val name = "Alex"
            fun develop(){
                Log.w("anonym class", "called develop fun")
            }
        }

        val person2 = object: Developer {
            val name = "Jack"
            override fun develop() {
                Log.w("anonym class", "called develop fun DEVELOPER")
            }
        }

        val house = HausBuilder.Builder
            .setStock(3)
            .setSwimpool(false)
            .build()

        btn.setOnClickListener{
            Log.w("housebuilder", "${house.hasSwimpool()} ${house.howManyStocks()} ")
            person.develop()
            person2.name
            callAnonymClass(person2)
            callAnonymClass(object : Developer {
                override fun develop() {
                    Log.w("anonym class", "called develop fun DEVELOPER")
                }
            })
            Toast.makeText(this, person.name, Toast.LENGTH_SHORT).show()
        }


        val lambda = {string: String -> Toast.makeText(this, " your text is $string", Toast.LENGTH_SHORT).show()
        }
        lambda("Set text in lambda")

        makeRequest("https://google.com"){callBackResult ->
            Log.w("callBack result",callBackResult)
        }
        returnAnonymClass("set string in return fun").develop()
    }

    private fun returnAnonymClass(string: String) = object {
        fun develop(){
            Log.w("anonym class", "called develop fun from returnType $string")
        }
    }


    fun callAnonymClass(developer: Developer){
        developer.develop()
    }

    fun makeRequest(url: String, argForCallBack: (string: String) -> Unit){
        argForCallBack.invoke("callBackResult $url")
    }

    companion object{
        fun kotlinActivityStart(context: Context) {
            context.startActivity((Intent(context, KotlinActivity::class.java)))
        }
    }
}

interface Developer{
    fun develop()
}