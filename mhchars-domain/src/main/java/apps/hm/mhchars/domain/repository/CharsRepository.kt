package apps.hm.mhchars.domain.repository

import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import kotlinx.coroutines.flow.Flow

/**
 * Interface of Chars Repository to expose to other module
 */
interface CharsRepository {

    /**
     * Method to fetch the characters from Repository
     * @return Flow of Outputs with Characters list
     */
    suspend fun fetchCharacters(): Flow<Output<List<CharacterEntity>>>
}