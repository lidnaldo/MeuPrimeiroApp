package br.iesb.meuprimeiroapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.iesb.meuprimeiroapp.R
import br.iesb.meuprimeiroapp.domain.RegisterData
import br.iesb.meuprimeiroapp.domain.RegisterResult
import br.iesb.meuprimeiroapp.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var viewmodel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btRegister.setOnClickListener{ cadastrar() }
        btBackRegister.setOnClickListener { voltar() }

        viewmodel = RegisterViewModel(application)
        viewmodel.resultadoParaTela.observe(this) { resultadoCadastro ->
            processarResultRegister(resultadoCadastro)
        }
    }

    fun processarResultRegister(res: RegisterResult){
        //mensagem de erro para usuario atraves de Toast
        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }

        //sucesso ir para tela de RegisterActivity
        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)
        finish() //finalizar pilha! Evitar que, ao usuario clicar em voltar no aparelho, ele retorne para tela de login novamente
    }

    fun cadastrar() {
        val email = etEmailRegister.text.toString()
        val pass = etPasswordRegister.text.toString()
        val confirmPass = etPasswordConfirmRegister.text.toString()

        val dataRegister = RegisterData(email, pass, confirmPass)
        viewmodel.cadastrar(dataRegister)

        viewmodel = RegisterViewModel(application)
        viewmodel.resultadoParaTela.observe(this) { resultado ->
            processarResultCadastrar(resultado)
        }

    }

    fun processarResultCadastrar(res: RegisterResult){
        //mensagem de erro para usuario atraves de Toast
        Toast.makeText(this, "pelas barbas do profeta", Toast.LENGTH_LONG).show()
        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(this, res.result, Toast.LENGTH_LONG).show()
        val intentHome = Intent(this, HomeActivity::class.java)
        Toast.makeText(this, "pelas barbas do profeta", Toast.LENGTH_LONG).show()
        startActivity(intentHome)
        Toast.makeText(this, res.result, Toast.LENGTH_LONG).show()
        finish() //finalizar pilha! Evitar que, ao usuario clicar em voltar no aparelho, ele retorne para tela de login novamente
    }

    fun voltar() {
        finish() //finalizar pilha
    }
}