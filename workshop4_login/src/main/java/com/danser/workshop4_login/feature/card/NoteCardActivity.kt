package com.danser.workshop4_login.feature.card

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danser.workshop4_login.R

class NoteCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_card)

        val fragment = NoteCardFragment()
        fragment.arguments = intent.extras

        supportFragmentManager
            .beginTransaction()
            .add(R.id.vContainer, fragment)
            .commit()
    }
}
