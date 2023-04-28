package com.example.formulario.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import com.example.formulario.Model.Alumno
import com.example.formulario.R
import com.example.formulario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            val transaction=supportFragmentManager.beginTransaction()
            val firstFragment =FormFragment()
            transaction.add(R.id.FormFragmentContainer,firstFragment).commit()
        }



    }

}