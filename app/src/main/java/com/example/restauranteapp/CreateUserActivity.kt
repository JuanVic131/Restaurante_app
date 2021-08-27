package com.example.restauranteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_user.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap


class CreateUserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth;
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        auth = Firebase.auth

        btn_register.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            Toast.makeText(this, "Sesión iniciada", Toast.LENGTH_LONG).apply {  }.show()
            // Firebase.auth.signOut()
        }
    }

    private fun createAccount() {

        if ( et_name.text.toString().isEmpty()
            || et_address.text.toString().isEmpty()
            || et_email.text.toString().isEmpty()
            || et_password.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "Uno de los campos se encuentran vacios", Toast.LENGTH_SHORT).apply {  }.show()
            return
        }

        val email = et_email.text.toString()
        val password = et_password.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Si se crea el usuario se va a crear un map para guardar los otros datos
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("DEBUG ->", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            return
        }

        val datauser = hashMapOf(
            "username" to et_name.text.toString(),
            "address" to et_address.text.toString(),
        )

        // Se guardaran los otros datos del usuario
        db.collection("users").document(user.uid)
            .set(datauser)
    }

    private fun reload() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            btn_register.id -> createAccount()
            btn_cancel.id -> goLogin()
        }
    }

    private fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java).apply {  })
    }
}