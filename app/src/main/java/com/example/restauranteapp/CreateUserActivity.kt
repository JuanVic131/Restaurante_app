package com.example.restauranteapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        // Se instancia el auth de firebase

        btn_register.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            btn_register.id -> showPolicies()
            btn_cancel.id -> goLogin()
        }
    }

    // Se verificaran si los campos se encuentran vacios y además mostrará el alert dialog
    private fun showPolicies() {
        // Se verifica si los campos no se encuentren vacios
        if ( et_name.text.toString().isNullOrEmpty()
            || et_address.text.toString().isNullOrEmpty()
            || et_email.text.toString().isNullOrEmpty()
            || et_password.text.toString().isNullOrEmpty()
        ) {
            Toast.makeText(this, R.string.empty_field, Toast.LENGTH_SHORT).apply {  }.show()
            return
        }

        // --- Pruebas de alert dialog
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle(R.string.policies_title)
            setMessage(R.string.policies_message)
                .setPositiveButton(
                    R.string.accept,
                    DialogInterface.OnClickListener { dialog, id ->
                        createAccount()
                    }
                )
                    // Si el usuario no acepta las condiciones no se registrará el usuario
                .setNegativeButton(
                    R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(baseContext, R.string.extra_message, Toast.LENGTH_SHORT).show()
                    }
                )
        }.create().show()
    }

    // Cuando se presione el botón de cancelar en la activity de registro
    private fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java).apply {  })
    }

    // Creacion de la cuenta
    private fun createAccount() {
        val email = et_email.text.toString()
        val password = et_password.text.toString()

    }

    // Cargará la siguiente pantalla
    private fun complete() {
        Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()

        // Se limpian los edit text
        val clear : String = ""
        et_email.setText(clear)
        et_name.setText(clear)
        et_password.setText(clear)
        et_address.setText(clear)
    }
}