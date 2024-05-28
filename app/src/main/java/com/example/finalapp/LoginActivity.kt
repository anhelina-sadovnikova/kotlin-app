package com.example.finalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finalapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        loginBinding.buttonSignIn.setOnClickListener{

            val userEmail = loginBinding.editTextLoginEmail.text.toString()
            val userPassword = loginBinding.editTextLoginPassword.text.toString()

            signInUser(userEmail, userPassword)

        }
        loginBinding.textViewSignUp.setOnClickListener{

            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)

        }
        loginBinding.textViewForgotPassword.setOnClickListener {

            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)

        }
    }

    fun signInUser(userEmail : String, userPassword : String){

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener{ task ->

            if (task.isSuccessful){

                Toast.makeText(applicationContext, "Welcome to the Game", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{

                Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()

            }

        }

    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser
        if (user != null){
            Toast.makeText(applicationContext, "Welcome to the Game", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}