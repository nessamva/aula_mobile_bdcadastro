package br.unisanta.bdcadastro.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "nome")
    val nome: String,

    @ColumnInfo(name = "idade")
    val idade: Int,

    @ColumnInfo(name = "telefone")
    val telefone: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "senha")
    val senha: String,

    @ColumnInfo(name = "curso")
    val curso: String
)

