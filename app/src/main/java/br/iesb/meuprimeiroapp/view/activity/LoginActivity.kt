package br.iesb.meuprimeiroapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.iesb.meuprimeiroapp.R
import br.iesb.meuprimeiroapp.domain.LoginData
import br.iesb.meuprimeiroapp.domain.LoginResult
import br.iesb.meuprimeiroapp.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var viewmodel: LoginViewModel

    /* 1º do ciclo de vida do APP */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /* setOnClickListener - eventos  */
        btLogin.setOnClickListener { login() }
        tvForgotPassword.setOnClickListener { forgotPassword() }
        tvRegister.setOnClickListener { register() }

        viewmodel = LoginViewModel(application)
        viewmodel.resultadoParaTela.observe(this) { resultado ->
            processarResultLogin(resultado)
        }

    }

    /*
    /* 2º do ciclo de vida do APP */
    override fun onStart() {
        super.onStart()
    }

    /* APP em standBy (aguardando input do usuário por exemplo) */
    override fun onResume() {
        super.onResume()
    }

    /* APP  em 2º plano (ex.: recebe ligacao, o APP fica parcialmente coberto  por outro app)*/
    override fun onPause() {
        super.onPause()
    }

    /* quando APP fica em segundo plano (usuário por exemplo "minimizou o app") */
    override fun onStop() {
        super.onStop()
    }

    /* APP foi fechado e saiu da memória */
    override fun onDestroy() {
        super.onDestroy()
    }
    */


    fun processarResultLogin(res: LoginResult){
        //mensagem de erro para usuario atraves de Toast
        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }

        val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish() //finalizar pilha! Evitar que, ao usuario clicar em voltar no aparelho, ele retorne para tela de login novamente
    }

    fun login() {
        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()

        val data = LoginData(email, pass)
        viewmodel.login(data)
    }

    fun forgotPassword() {
        val intetForgotPassword = Intent(this, ForgotPasswordActivity::class.java);
        startActivity(intetForgotPassword);
    }

    fun register() {
        //sucesso ir para tela de RegisterActivity
        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)
    }
}