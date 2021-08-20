package com.example.restauranteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Check if user is signed in (non-null) and update UI accordingly.
        // Check if user is signed in (non-null) and update UI accordingly.

        // val currentUser = mAuth!!.currentUser
        // updateUI(currentUser)
        btn_iniciar.setOnClickListener(this)
        btn_registrarse.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            btn_iniciar.id -> IniciarSesion()
            btn_registrarse.id -> viewRegistrar()
        }
    }

    // Iniciar sesión con Firebase
    private fun IniciarSesion() {

    }

    // Viajar al activity para registrar a usuario
    private fun viewRegistrar() {
        startActivity(Intent(this, RegistrarUsuarioActivity::class.java).apply {  })
    }
}