package com.danser.workshop4_login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var prefsRepo: PrefsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inject()
        bindViews()
    }

    private fun inject() {
        prefsRepo = PrefsRepository(this)
    }

    private fun bindViews() {
        bLogin.setOnClickListener {
            login(
                login = etLogin.text.toString(),
                password = etPassword.text.toString()
            )
        }
        bRegister.setOnClickListener {
            openRegisterScreen()
        }
    }

    private fun login(login: String, password: String) {
        val isLoggedIn = prefsRepo.hasString(key = login, value = password)

        if (isLoggedIn) {
            //open main screen
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            //show error
            Snackbar.make(
                vRoot,
                "Login or password is incorrect,\nplease try again",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun openRegisterScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
