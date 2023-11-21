package com.sena.fitapp.view

import android.content.Intent


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.sena.fitapp.MainActivity


import com.sena.fitapp.R

class Login : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: TextView
    private lateinit var loginButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, CadastroInicial::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.registrarNow)

        textView.setOnClickListener {
            val intent = Intent(this, Registrar::class.java)
            startActivity(intent)
        }



        loginButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            when {
                email.isEmpty() -> {
                    Registrar.mensagem(it, "Coloque seu login!")
                }

                password.isEmpty() -> {
                    Registrar.mensagem(it, "Coloque sua senha!")
                }

                password.length <= 5 -> {
                    Registrar.mensagem(it, "A senha precisa ter pelo menos 6 caracteres")
                }

                else -> {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            progressBar.visibility = View.GONE
                            if (task.isSuccessful) {
                                Toast.makeText(applicationContext, "Logado", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                            } else {

                                Toast.makeText(
                                    this,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT,
                                ).show()

                            }
                        }
                }

            }
        }
    }
}