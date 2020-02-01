package com.danser.workshop4_login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danser.workshop4_login.RegisterActivity.Companion.REGISTERED_CODE
import com.danser.workshop4_login.di.injector
import com.danser.workshop4_login.di.module.ApplicationComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ApplicationComponent by injector.applicationModule {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REGISTER_REQUEST_CODE) {
            if (resultCode == REGISTERED_CODE) {
                openNotesFeed()
            }
        }
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
            //open notes screen and close this
            openNotesFeed()
        } else {
            //show error
            Snackbar.make(
                vRoot,
                "Login or password is incorrect,\nplease try again",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun openNotesFeed() {
        finish()
        val intent = Intent(this, NotesFeedActivity::class.java)
        startActivity(intent)
    }

    private fun openRegisterScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivityForResult(intent, REGISTER_REQUEST_CODE)
    }

    companion object {
        const val REGISTER_REQUEST_CODE = 1
    }
}
