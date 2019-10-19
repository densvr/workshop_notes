package com.university.workshops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a = 123
        a += 1
        Log.d("MainActivity", "a == $a")
        Log.d("MainActivity", "a == $a")
        Log.d("MainActivity", "Hello, android world!!")
    }
}
