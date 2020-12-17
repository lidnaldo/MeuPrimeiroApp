package br.iesb.meuprimeiroapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.iesb.meuprimeiroapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*


class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        bt_EsqueciSenha.setOnClickListener {
            resgatarSenha()
        }
    }

    fun resgatarSenha(){
        val email = ti_emailEsqueciSenha.text.toString();

        val autenticacao = FirebaseAuth.getInstance();
        val operacao = autenticacao.sendPasswordResetEmail(email);
        operacao.addOnCompleteListener { resultado ->
            if(resultado.isSuccessful){
                Toast.makeText(this, "CERTO: Senha enviada para usuario cadastrado", Toast.LENGTH_LONG).show();
                val navegaParaLogin = Intent(this, LoginActivity::class.java);
                startActivity(navegaParaLogin);
            }else{
                Toast.makeText(this, "Erro: Usuário inexistentes", Toast.LENGTH_LONG).show();
            }
        }

    }
}