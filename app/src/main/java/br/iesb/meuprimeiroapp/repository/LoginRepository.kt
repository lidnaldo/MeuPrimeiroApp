package br.iesb.meuprimeiroapp.repository

import android.content.Intent
import android.widget.Toast
import br.iesb.meuprimeiroapp.domain.LoginData
import br.iesb.meuprimeiroapp.domain.LoginResult
import br.iesb.meuprimeiroapp.view.activity.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository {
    suspend fun login(data: LoginData): LoginResult {
        //retorno de "promisse"
        return suspendCoroutine { resultadoPromisse ->
             //LOGIN COM O FIREBASE
            val auth = FirebaseAuth.getInstance()
            val operacao = auth.signInWithEmailAndPassword(data.email, data.pass)
            operacao.addOnCompleteListener { resultado ->
                val resLogin = LoginResult()
                if (resultado.isSuccessful) {
                    //sucesso ir para tela de HomeActivity
                    resLogin.result = "LOGIN_FIREBASE_SUCESSO"
                } else {
                    //mensagem de erro para usuario atraves de Toast
                    resLogin.error = "ERRO_LOGIN_FIREBASE"
                }

                resultadoPromisse.resume(resLogin)
            }
        }

    }
}