package com.jdev.firebase_auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.FirebaseDatabase




class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Inventario")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()


        btnIniciar.setOnClickListener {

            var user = txtUsuario.text.toString();
            var pass = txtContrasena.text.toString();

            signInUser(user, pass);
            myRef.child(myRef.push().key.toString()).setValue("Primer valor")
        }
    }

    fun signInUser(user : String, pass : String){
        mAuth?.signInWithEmailAndPassword(user,pass)?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = mAuth?.getCurrentUser()
            } else {
                Toast.makeText(this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }//signInUser

    fun signUpUser(user : String, pass : String){
      mAuth?.signInWithEmailAndPassword(user, pass)?.addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
            val user = mAuth?.getCurrentUser()
        } else {
            Toast.makeText(this, "Authentication failed.",
                Toast.LENGTH_SHORT).show()
        }
      }
    }//signUpUser

}
