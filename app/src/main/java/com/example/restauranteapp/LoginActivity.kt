package com.example.restauranteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializamos el auth de firebase

        // val currentUser = mAuth!!.currentUser
        // updateUI(currentUser)
        btn_iniciar.setOnClickListener(this)
        btn_registrarse.setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        /*
        val currentUser = auth.currentUser
        if(currentUser != null){

            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null ) {
                        println("DEBUG ->  ${document.data}")
                        Toast.makeText(baseContext, "Sesión ya iniciada", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(baseContext, MainActivity::class.java).apply {  })
                    }
                    println("DEBUG ->  ${document}")
                }
        }
        */
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            btn_iniciar.id -> IniciarSesion()
            btn_registrarse.id -> viewRegistrar()
        }
    }

    // Iniciar sesión con Firebase
    private fun IniciarSesion() {
        // Verificar si los campos se encuentran vacios

        if ( et_email.text.isNullOrBlank() || et_password.text.isNullOrBlank()) {
            Toast.makeText(baseContext, R.string.empty_field, Toast.LENGTH_SHORT).show()
            return
        }
    }

    // Viajar al activity para registrar a usuario
    private fun viewRegistrar() {
        startActivity(Intent(this,  CreateUserActivity::class.java).apply {  })
    }
}