package com.danser.workshop4_login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindViews()
    }


    private fun bindViews() {
        bLogin.setOnClickListener {
            login(
                login = etLogin.text.toString(),
                password = etPassword.text.toString()
            )
        }
    }

    private fun login(login: String, password: String) {
        if (login == LOGIN && password == PASSWORD) {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Snackbar.make(
                vRoot,
                "Login or password is incorrect,\nplease try again",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        private const val LOGIN = "petr"
        private const val PASSWORD = "qwerty"
    }
}
