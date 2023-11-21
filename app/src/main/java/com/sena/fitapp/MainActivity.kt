package com.sena.fitapp



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sena.fitapp.view.Alterar
import com.sena.fitapp.view.CadastroInicial
import com.sena.fitapp.view.Listar
import com.sena.fitapp.view.Login


class MainActivity : AppCompatActivity() {

    private lateinit var buttonCadastrarMedidasIniciais : Button
    private lateinit var buttonCadastrarMedidas : Button
    private lateinit var logoutButton: Button
    private lateinit var buttonListar: Button
    private lateinit var buttonAlterar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonListar = findViewById(R.id.buttonListar)
        buttonAlterar = findViewById(R.id.buttonAlterar)
        logoutButton = findViewById(R.id.logoutButton)
        buttonCadastrarMedidasIniciais = findViewById(R.id.buttonCadastrarMedidasIniciais)
        buttonCadastrarMedidas = findViewById(R.id.buttonCadastrarMedidas)



        buttonCadastrarMedidasIniciais.setOnClickListener {
            val intent = Intent(this, CadastroInicial::class.java)
            startActivity(intent)
        }



        buttonListar.setOnClickListener {
            val intent = Intent(this, Listar::class.java)
            startActivity(intent)
        }

        buttonAlterar.setOnClickListener {
            val intent = Intent(this, Alterar::class.java)
            startActivity(intent)
        }



        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        }
    }}

