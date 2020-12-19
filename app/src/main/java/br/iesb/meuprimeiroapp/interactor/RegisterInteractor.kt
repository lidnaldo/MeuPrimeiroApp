package br.iesb.meuprimeiroapp.interactor

import br.iesb.meuprimeiroapp.domain.RegisterData
import br.iesb.meuprimeiroapp.domain.RegisterResult
import br.iesb.meuprimeiroapp.repository.RegisterRepository

class RegisterInteractor {
    // variavel que vincula a interactor com a repository
    var repo = RegisterRepository()

    // data class Login:classe de dados p/ concentrar os atributos para login e retorno de criticas/sucesso
    suspend fun cadastrar(data: RegisterData): RegisterResult {

        val resultado = RegisterResult()

        /* ****  VALIDACAO DE CAMPOS DO LOGIN  ****************/
        if (data.email.isBlank() ){
            resultado.error = "ERRO_EMAIL_VAZIO"
            return resultado
        }
        if (data.pass.isBlank() ){
            resultado.error = "ERRO_SENHA_VAZIA"
            return resultado
        }
        if (data.pass.length < 6 ){
            resultado.error = "ERRO_SENHA_MENOR_6"
            return resultado
        }
        if (data.pass != data.confirmPass ){
            resultado.error = "ERRO_CONFIRMACAO_SENHA"
            return resultado
        }

        //após cricitas passar dados para repository
        val resultadoRepo =  repo.cadastrar(data)

        return resultadoRepo
    }
}