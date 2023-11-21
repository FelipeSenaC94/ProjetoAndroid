package com.sena.fitapp.model

data class UsuarioModel(
    var userId : String? = null,
    var cadastroId: String? = null,
    var salvarAltura: String? = null,
    var salvarIdade: String? = null,
    var salvarPesoInicial: String? = null,
    var salvarSexo: String? = null,
    var cadastrado: Boolean = true
)