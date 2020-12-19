package br.iesb.meuprimeiroapp.interactor

import br.iesb.meuprimeiroapp.domain.PersonagemData
import br.iesb.meuprimeiroapp.repository.ApiRepository

class ApiInteractor {

    private val repo = ApiRepository()

    suspend fun chamarAPI(): List<PersonagemData>{
        return repo.chamarAPI()
    }
}