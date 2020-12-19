package br.iesb.meuprimeiroapp.repository.dto

import java.util.*

// https://rickandmortyapi.com/api/character
data class CharacterDTO(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: OriginDTO,
    var image: String,
    var episode: List<String>,
    var url: String
)
