package com.example.formulario.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formulario.R
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.coroutines.coroutineContext

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intentSplash = Intent(this,MainActivity::class.java)
        thread {
            Thread.sleep(3000)
            startActivity(intentSplash)
            finish()
        }

    }
}