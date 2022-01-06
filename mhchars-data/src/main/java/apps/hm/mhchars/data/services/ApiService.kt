package apps.hm.mhchars.data.services


import apps.hm.mhchars.domain.model.CharacterEntity
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API Service
 */
interface ApiService {

    @GET("/api/moneyheist/characters.json")
    suspend fun getCharacters(): Response<List<CharacterEntity>>
}