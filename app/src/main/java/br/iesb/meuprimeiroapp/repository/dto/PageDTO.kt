package br.iesb.meuprimeiroapp.repository.dto
 //  https://rickandmortyapi.com/api/character
data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

// https://rickandmortyapi.com/api/location
data class PageDTO(
    val info: Info,
    val results: List<CharacterDTO>
)