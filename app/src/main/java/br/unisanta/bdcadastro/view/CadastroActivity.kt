package br.unisanta.bdcadastro.view

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.bdcadastro.databinding.ActivityCadastroBinding
import br.unisanta.bdcadastro.model.User
import br.unisanta.bdcadastro.db.AppDatabase


class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDao = AppDatabase.getInstance(this).userDao()

        binding.btnSalvar.setOnClickListener {
            val nome = binding.nomeInput.text.toString()
            val idade = binding.idadeInput.text.toString().toIntOrNull() ?: 0
            val telefone = binding.telefoneInput.text.toString()
            val email = binding.emailInput.text.toString()
            val senha = binding.senhaInput.text.toString()
            val curso = binding.cursoInput.text.toString()

            if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
                Toast.makeText(this, "Preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email inválido. Digite um email válido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val novoUsuario = User(
                nome = nome,
                idade = idade,
                telefone = telefone,
                email = email,
                senha = senha,
                curso = curso
            )

            userDao.inserir(novoUsuario)
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
            finish() // Fecha a tela e volta para anterior
        }
    }
}