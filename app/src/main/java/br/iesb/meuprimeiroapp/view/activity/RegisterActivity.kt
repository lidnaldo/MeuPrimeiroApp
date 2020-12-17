package br.iesb.meuprimeiroapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.iesb.meuprimeiroapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btRegister.setOnClickListener{ cadastrar() }
        btBackRegister.setOnClickListener { voltar() }
    }

    fun cadastrar() {
        val email = etEmailRegister.text.toString()
        val pass = etPasswordRegister.text.toString()
        val passConfirm = etPasswordConfirmRegister.text.toString()

        /* ****  VALIDACAO DE CAMPOS DO REGISTER  ****************/
        if (email.isBlank() ){
            val text = getString(R.string.email_required) //buscar texto de validação no arq string
            Toast.makeText( this , text, Toast.LENGTH_LONG).show()
            return
        }
        if (pass.isBlank() ){
            val text = getString(R.string.password_required) //buscar texto validação no arq string
            Toast.makeText( this , text, Toast.LENGTH_LONG).show()
            return
        }
        if (pass.length < 6 ){
            Toast.makeText( this , "Senha com mínimo de 6 caracteres", Toast.LENGTH_LONG).show()
            return
        }
        if (passConfirm != pass ){
            Toast.makeText( this , "Confirmação de senha e senha devem ser iguais!", Toast.LENGTH_LONG).show()
            return
        }

        val auth = FirebaseAuth.getInstance()
        val operacao = auth.createUserWithEmailAndPassword(email, pass)
        //createUserWith... cria e já loga o usuário ao mesmo tempo em caso de sucesso
        operacao.addOnCompleteListener{
            operacao.addOnCompleteListener() { resultado ->
                if (resultado.isSuccessful) {
                    //sucesso ir para tela de HomeActivity
                    Toast.makeText(this, "Usuário criado com sucesso ", Toast.LENGTH_LONG).show()
                    finish() //finalizar pilha! Evitar que, ao usuario clicar em voltar no aparelho, ele retorne para tela de cadastro
                } else {
                    //mensagem de erro para usuario atraves de Toast
                    Toast.makeText(this, "Erro ao criao o usuário: ${resultado.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
                }
             }
        }
    }


    fun voltar() {
        finish() //finalizar pilha
    }
}