package com.sena.fitapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.sena.fitapp.R
import java.text.NumberFormat

class Resultado : AppCompatActivity() {
    lateinit var txtResultIMC : TextView
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        logoutButton = findViewById(R.id.logoutButton)
        txtResultIMC = findViewById(R.id.txtResultIMC)



        txtResultIMC.text = "Seu imc é:"

        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        }
    }
}