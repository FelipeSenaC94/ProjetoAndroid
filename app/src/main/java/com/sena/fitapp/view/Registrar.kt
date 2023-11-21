package com.sena.fitapp.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.sena.fitapp.MainActivity
import com.sena.fitapp.R

class Registrar : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: TextView
    private lateinit var buttonRegistrar: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        auth = Firebase.auth
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.loginNow)

        textView.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


        buttonRegistrar.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            when {
                email.isEmpty() -> {
                    mensagem(it, "Coloque seu login!")
                }

                password.isEmpty() -> {
                    mensagem(it, "Coloque sua senha!")
                }

                password.length <= 5 -> {
                    mensagem(it, "A senha precisa ter pelo menos 6 caracteres")
                }

                else -> {
                    navegarPraHome()
                }
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Conta criada com sucesso!",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)

                    } else {


                        Toast.makeText(
                            this,
                            "Erro",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }
    }

    companion object{
     fun mensagem(view: View, message: String) {

        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)


        snackbar.view.setBackgroundColor(Color.parseColor("#FF0000")) // Set background tint
        snackbar.setTextColor(Color.parseColor("#FFFFFF")) // Set text color
        snackbar.show()
    }
    }
    private fun navegarPraHome() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }


}