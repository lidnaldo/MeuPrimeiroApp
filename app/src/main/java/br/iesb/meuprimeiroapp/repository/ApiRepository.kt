package br.iesb.meuprimeiroapp.repository

import br.iesb.meuprimeiroapp.domain.PersonagemData
import br.iesb.meuprimeiroapp.repository.dto.PageDTO
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/* ** CRIANDO OS END POINTS *************************************** */
/* ******** API ---> https://rickandmortyapi.com/api/ ************* */
interface RickMortyPersonagensApi {
    @GET("character")  //https://rickandmortyapi.com/api/character
    @Headers("Content-Type: application/json")
    //https://rickandmortyapi.com/api/character?page=2
    suspend fun recuperarPersonagens (@Query("page") pagina: Int): PageDTO

}

interface RickMortyApiEpisodiosLocalizacoes {
    @GET("location")    //https://rickandmortyapi.com/api/location
    suspend fun recuperarLocalizacoes ()

    @GET("episode")    //https://rickandmortyapi.com/api/episode
    suspend fun recuperarEpisodios ()

}

class ApiRepository {
    // https://rickandmortyapi.com/api/

    //configurar e conector para chamar a API ( retrofit )
    private val conector: Retrofit
    init {
//         PASSAR CAMPO CREATED DO TIPO DATE PARA O RETROFIT
        val gsonConfig = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        conector= Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gsonConfig))
            .build()

//        conector= Retrofit.Builder()
//            .baseUrl("https://rickandmortyapi.com/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
    }

    suspend fun chamarAPI(): List<PersonagemData>{
        // vincular retrofit com a API
        val service = conector.create(RickMortyPersonagensApi::class.java)

        //https://rickandmortyapi.com/api/character?page=1
        val listaPersonagens = service.recuperarPersonagens(1).results

        // fazer conversao de DTO para formato PersonagemData
        return listaPersonagens.map { dto ->
            PersonagemData(
                nome = dto.name,
                genero = dto.gender,
                tipo = dto.type,
                foto = dto.image
            )
        }
    }
}

