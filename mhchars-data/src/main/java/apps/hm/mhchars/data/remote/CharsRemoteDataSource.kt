package apps.hm.mhchars.data.remote

import apps.hm.mhchars.data.BaseRemoteDataSource
import apps.hm.mhchars.data.services.ApiService
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * RemoteDataSource of Characters API service
 * @param apiService the object of api service
 */
class CharsRemoteDataSource @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {

    /**
     * Method to fetch the characters from CharsRemoteDataSource
     * @return Outputs with list of Characters
     */
    suspend fun fetchCharacters(): Output<List<CharacterEntity>> {
        return getResponse(
            request = { apiService.getCharacters() },
            defaultErrorMessage = "Error fetching Characters"
        )
    }
}