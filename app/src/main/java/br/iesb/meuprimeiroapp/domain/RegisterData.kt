package br.iesb.meuprimeiroapp.domain

// classe de dados:  concentrar os atributos necessarios para Cadastro
data class RegisterData(
    var email:String,
    var pass:String,
    var confirmPass:String
)