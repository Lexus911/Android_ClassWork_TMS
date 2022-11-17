package com.example.kotlinapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.kotlinapp.MainActivity2.Companion.startMainActivity2

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(getString(R.string.blue))))
        supportActionBar?.title = "Add contact"

        val btnGoToActivity2 = findViewById<Button>(R.id.btnGoToActivity2)

        btnGoToActivity2.setOnClickListener{
            startActivity(
                Intent(this, MainActivity2::class.java)
//                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                //.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY) //MainActivity2 не будет добавлена в бэкстек
            )
        }

        val textView = findViewById<TextView>(R.id.textView)

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener{
            textView.text = getString(R.string.Queen)

            startMainActivity2(this, textView.text.toString() + getString(R.string.from_MainActivity))

        }
    }


}