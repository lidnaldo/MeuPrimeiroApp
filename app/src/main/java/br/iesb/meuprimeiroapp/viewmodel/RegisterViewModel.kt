package br.iesb.meuprimeiroapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.meuprimeiroapp.R
import br.iesb.meuprimeiroapp.domain.RegisterData
import br.iesb.meuprimeiroapp.domain.RegisterResult
import br.iesb.meuprimeiroapp.interactor.RegisterInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RegisterViewModel (val app: Application):AndroidViewModel(app), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val interactor = RegisterInteractor()

    val resultadoParaTela = MutableLiveData<RegisterResult>()

    fun cadastrar(data: RegisterData) {
        launch {
            val resultado = interactor.cadastrar(data)
            if (resultado.error != null){
                if (resultado.error == "ERRO_EMAIL_VAZIO"){
                    resultado.error = app.getString(R.string.email_required)
                } else if (resultado.error == "ERRO_SENHA_VAZIA"){
                    resultado.error = app.getString(R.string.password_required)
                } else if (resultado.error == "ERRO_CONFIRMACAO_SENHA"){
                    resultado.error = app.getString(R.string.password_confirm)
                } else if (resultado.error == "ERRO_SENHA_MENOR_6"){
                    resultado.error = app.getString(R.string.password_6_char)
                } else if (resultado.error == "ERRO_AO_CRIAR_USUARIO"){
                    resultado.error = app.getString(R.string.register_error)
                }
            } else {
                resultado.error = null
                resultado.result = "Cadastro & Login feitos com sucesso!"
            }
            resultadoParaTela.value = resultado

        }
    }
}