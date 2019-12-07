package com.danser.workshop4_login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danser.workshop4_login.data.PrefsRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.etLogin
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.vRoot

class RegisterActivity : AppCompatActivity() {

    lateinit var prefsRepo: PrefsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        inject()
        bindViews()
    }

    private fun inject() {
        prefsRepo = PrefsRepository(this)
    }

    private fun bindViews() {
        bRegister.setOnClickListener {
            register(
                login = etLogin.text.toString(),
                password = etPassword.text.toString(),
                repeatedPassword = etPassword2.text.toString()
            )
        }
    }

    private fun register(login: String, password: String, repeatedPassword: String) {
        if (login.isBlank()) {
            showSnack("Couldn't register with empty login")
            return
        }
        if (password.isBlank()) {
            showSnack("Couldn't register with empty password")
            return
        }
        if (password != repeatedPassword) {
            showSnack("Couldn't register, passwords should be the same")
            return
        }

        prefsRepo.saveStringAsync(login, password)
        finish()
    }

    private fun showSnack(text: String) {
        Snackbar.make(
            vRoot,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}
