package apps.hm.mhchars.data.remote

import apps.hm.mhchars.data.BaseRemoteDataSource
import apps.hm.mhchars.data.services.ApiService
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import retrofit2.Retrofit
import javax.inject.Inject

class CharsRemoteDataSource @Inject constructor(
    private val profService: ApiService, retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {

    suspend fun fetchCharacters(): Output<List<CharacterEntity>> {
        return getResponse(
            request = { profService.getCharacters() },
            defaultErrorMessage = "Error fetching Characters"
        )
    }
}