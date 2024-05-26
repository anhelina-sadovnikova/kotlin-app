package com.example.finalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.finalapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    lateinit var signUpBinding: ActivitySignUpBinding

    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)

        signUpBinding.buttonSignUp.setOnClickListener {

            val email = signUpBinding.editTextSignupEmail.text.toString()
            val password = signUpBinding.editTextSignupPassword.text.toString()

            signupWithFirebase(email, password)
        }
    }

    fun signupWithFirebase(email : String, password : String) {

        signUpBinding.progressBarSignup.visibility = View.VISIBLE
        signUpBinding.buttonSignUp.isClickable = false

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->

            if(task.isSuccessful)
            {

                Toast.makeText(applicationContext,"Your account has been created", Toast.LENGTH_SHORT).show()
                finish()
                signUpBinding.progressBarSignup.visibility = View.INVISIBLE
                signUpBinding.buttonSignUp.isClickable = true

            }else{

                Toast.makeText(applicationContext,task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        }
    }
}