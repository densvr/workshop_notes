package com.university.workshops

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bSecondScreen.setOnClickListener { openSecondScreen() }
    }

    private fun openSecondScreen() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}
