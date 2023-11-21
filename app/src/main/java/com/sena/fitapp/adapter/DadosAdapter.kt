package com.sena.fitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sena.fitapp.R
import com.sena.fitapp.model.UsuarioModel
import com.sena.fitapp.view.ListarVazio
import org.w3c.dom.Text

class DadosAdapter(private var listaUsuarios: ArrayList<UsuarioModel>) :
    RecyclerView.Adapter<DadosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_listar_vazio, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = listaUsuarios[position]


          holder.textViewPeso.text = "Peso Inicial: ${usuario.salvarPesoInicial}"

    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }
    fun setDados(novaListaUsuarios: ArrayList<UsuarioModel>) {
        listaUsuarios = novaListaUsuarios
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPeso: TextView = itemView.findViewById(R.id.listarPeso)
    }
}