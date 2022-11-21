package com.example.kotlinapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kotlinapp.KotlinActivity.Companion.kotlinActivityStart
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val btn = findViewById<Button>(R.id.btnGoToActivity1)
        btn.setOnClickListener{
            kotlinActivityStart(this)
        }

        val editText = findViewById<TextInputEditText>(R.id.et_text)
        val editText2 = findViewById<TextInputEditText>(R.id.et_text2)
        val button = findViewById<Button>(R.id.btn_displayText)
        val textView = findViewById<TextView>(R.id.tv_text)
        val rb1 = findViewById<RadioButton>(R.id.radioButton1)
        val rb2 = findViewById<RadioButton>(R.id.radioButton2)
        val layout1 = findViewById<TextInputLayout>(R.id.textInputLayout)
        val layout2 = findViewById<TextInputLayout>(R.id.textInputLayout2)


        rb1.setOnClickListener{
            if(rb1.isChecked){
                rb2.isChecked = false

            }else{
                rb1.isChecked = true
            }

        }
        rb2.setOnClickListener{
            if(rb2.isChecked){
                rb1.isChecked = false

            }else{
                rb2.isChecked = true
            }

        }

        val dialog = AlertDialog.Builder(this)
            .setTitle("Information")
            .setMessage("I am android developer")
            .setCancelable(false)
            .setPositiveButton("OK"){dialog, _ ->
                Toast.makeText(this, "called positive", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel"){dialog, _ ->
                dialog.cancel()
            }

        button.setOnClickListener{
            dialog.show()

            if(editText.text.toString().isEmpty()){

                layout1.setErrorIconDrawable(R.drawable.ic_warning)
                editText.error = "name cant be empty"

                }else if(editText2.text.toString().isEmpty()){
                layout2.setErrorIconDrawable(R.drawable.ic_warning)
                editText2.error = "password cant be empty"
                }
            else {
                textView.text = "${editText.text.toString()} ${editText2.text.toString()}"


            }

        }






    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }



}