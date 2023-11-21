package com.sena.fitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.sena.fitapp.R
import com.sena.fitapp.adapter.DadosAdapter
import com.sena.fitapp.model.UsuarioModel

class Listar : AppCompatActivity() {

    private lateinit var recyclerListarVazio: RecyclerView
    private lateinit var adapter: DadosAdapter
    private lateinit var databaseReference: DatabaseReference
    private val usuariosList = ArrayList<UsuarioModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        recyclerListarVazio = findViewById(R.id.recyclerViewListar)
        recyclerListarVazio.layoutManager = LinearLayoutManager(this)
        recyclerListarVazio.setHasFixedSize(true)

        // Inicialize o adaptador uma vez
        adapter = DadosAdapter(usuariosList)
        recyclerListarVazio.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().reference.child("usuario")

        // Adicione um ValueEventListener para obter os dados do Firebase
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    usuariosList.clear()

                    for (data in dataSnapshot.children) {
                        val usuario = data.getValue(UsuarioModel::class.java)
                        if (usuario != null) {
                            usuariosList.add(usuario)
                        }
                    }

                    // Atualize a lista de dados no adaptador e notifique-o sobre as mudanças
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Lide com erros, se houver
            }
        })
    }
}