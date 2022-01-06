package apps.hm.mhchars.domain.repository

import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import kotlinx.coroutines.flow.Flow

interface CharsRepository {
    suspend fun fetchCharacters(): Flow<Output<List<CharacterEntity>>>
}