package com.sena.fitapp.view

import android.content.Intent
import android.health.connect.datatypes.units.Length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sena.fitapp.MainActivity

import com.sena.fitapp.R
import com.sena.fitapp.model.UsuarioModel

class CadastroInicial : AppCompatActivity() {
    private lateinit var pesoInicial: EditText
    private lateinit var altura: EditText
    private lateinit var idade: EditText
    private lateinit var logoutButton: Button
    private lateinit var dbRef: DatabaseReference
    private lateinit var spinner: Spinner
    private lateinit var sexo: String
    private lateinit var userId: String
    private lateinit var btSalvarDados : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_inicial)
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        val currentUser = FirebaseAuth.getInstance().currentUser
        dbRef = FirebaseDatabase.getInstance().getReference("Usuario").child(userId)

        userId = currentUser?.uid.toString()
        logoutButton = findViewById(R.id.logoutButton)
        pesoInicial = findViewById(R.id.pesoInicial)
        altura = findViewById(R.id.altura)
        idade = findViewById(R.id.editIdade)
        btSalvarDados = findViewById(R.id.btSalvarDados)
        spinner = findViewById<Spinner>(R.id.spinner_sexo)
        val items = listOf("Masculino", "Feminino")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        val escolha = spinner.selectedItem
        sexo = if (escolha == "Masculino") "M" else "F"


        btSalvarDados.setOnClickListener {
            salvarCadastroInicialData()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        }

    }


    private fun salvarCadastroInicialData() {
        val salvarAltura = altura.text.toString()
        val salvarIdade = idade.text.toString()
        val salvarPesoInicial = pesoInicial.text.toString()
        val salvarSexo = sexo
        val salvarUserId = userId



        if (salvarAltura.isEmpty()) {
            altura.error = "Insira altura"
        }
        if (salvarIdade.isEmpty()) {
            idade.error = "Insira idade"
        }
        if (salvarPesoInicial.isEmpty()) {
            pesoInicial.error = "Insira peso"
        }

        val cadastroId = dbRef.push().key!!

        val usuario =
            UsuarioModel(
                salvarUserId,
                cadastroId,
                salvarAltura,
                salvarIdade,
                salvarPesoInicial,
                salvarSexo,
                cadastrado = true
            )

        dbRef.child(cadastroId).setValue(usuario).addOnCompleteListener {
            Toast.makeText(
                this, "Informações salvas com sucesso",
                Toast.LENGTH_LONG
            ).show()
        }.addOnFailureListener { err ->
            Toast.makeText(this, "erro ${err.message}", Toast.LENGTH_LONG).show()
        }
    }

}