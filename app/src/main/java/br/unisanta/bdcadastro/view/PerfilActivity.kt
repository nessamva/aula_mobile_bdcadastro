package br.unisanta.bdcadastro.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.bdcadastro.R
import br.unisanta.bdcadastro.databinding.ActivityPerfilBinding
import br.unisanta.bdcadastro.db.AppDatabase
import br.unisanta.bdcadastro.model.User

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private var userId: Int = -1
    private lateinit var userDao: br.unisanta.bdcadastro.dao.UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getIntExtra("userId", -1)
        if (userId == -1) {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        userDao = AppDatabase.getInstance(this).userDao()
        val usuario = userDao.buscarPorId(userId)

        // Preenche os campos com os dados atuais
        binding.nomeInput.setText(usuario.nome)
        binding.idadeInput.setText(usuario.idade.toString())
        binding.telefoneInput.setText(usuario.telefone)
        binding.emailInput.setText(usuario.email)
        binding.cursoInput.setText(usuario.curso)

        binding.btnSalvar.setOnClickListener {
            val nome = binding.nomeInput.text.toString()
            val idade = binding.idadeInput.text.toString().toIntOrNull() ?: 0
            val telefone = binding.telefoneInput.text.toString()
            val email = binding.emailInput.text.toString()
            val curso = binding.cursoInput.text.toString()

            val usuarioAtualizado = User(
                id = userId,
                nome = nome,
                idade = idade,
                telefone = telefone,
                email = email,
                senha = usuario.senha, // mantém a senha original
                curso = curso
            )

            userDao.atualizar(usuarioAtualizado)
            Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show()
        }

        binding.btnSair.setOnClickListener {
            Toast.makeText(this, "Você saiu da conta", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}