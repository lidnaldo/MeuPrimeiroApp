package br.iesb.meuprimeiroapp.repository

import android.widget.Toast
import br.iesb.meuprimeiroapp.domain.LoginData
import br.iesb.meuprimeiroapp.domain.LoginResult
import br.iesb.meuprimeiroapp.domain.RegisterData
import br.iesb.meuprimeiroapp.domain.RegisterResult
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RegisterRepository {
    suspend fun cadastrar(data: RegisterData): RegisterResult {
        //retorno de "promisse"
        return suspendCoroutine { resultadoPromisse ->
            val auth = FirebaseAuth.getInstance()
            val operacao = auth.createUserWithEmailAndPassword(data.email, data.pass)
            //createUserWith... cria e já loga o usuário ao mesmo tempo em caso de sucesso
            operacao.addOnCompleteListener{
                operacao.addOnCompleteListener() { resultado ->
                    val resRegister = RegisterResult()
                    if (resultado.isSuccessful) {
                        //sucesso ir para tela de HomeActivity
                        resRegister.result = "USUARIO_CRIADO"
                    } else {
                        //mensagem de erro para usuario atraves de Toast
                        resRegister.error = "ERRO_AO_CRIAR_USUARIO"
                    }

                    resultadoPromisse.resume(resRegister)
                }
            }
        }

    }
}