package br.iesb.meuprimeiroapp.domain

data class RegisterResult(
    var result:String? = null ,   //? = null (ou exclusivo) --> retornará somente 1
    var error:String? = null
)