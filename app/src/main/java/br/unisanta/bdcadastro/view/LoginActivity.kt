package br.unisanta.bdcadastro.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.bdcadastro.R
import br.unisanta.bdcadastro.db.AppDatabase
import br.unisanta.bdcadastro.databinding.ActivityLoginBinding
import android.util.Patterns

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDao = AppDatabase.getInstance(this).userDao()

        binding.btnEntrar.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val senha = binding.senhaInput.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email inválido. Digite um email válido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuario = userDao.login(email, senha)

            if (usuario != null) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PerfilActivity::class.java)
                intent.putExtra("userId", usuario.id)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onResume() {
        super.onResume()
        binding.emailInput.text.clear()
        binding.senhaInput.text.clear()
    }

}