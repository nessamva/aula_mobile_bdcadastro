package br.unisanta.bdcadastro.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.unisanta.bdcadastro.model.User

@Dao
interface UserDao {
    @Insert
    fun inserir(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND senha = :senha")
    fun login(email: String, senha: String): User?

    @Update
    fun atualizar(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun buscarPorId(id: Int): User
}