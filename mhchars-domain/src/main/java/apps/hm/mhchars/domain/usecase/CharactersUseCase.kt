package apps.hm.mhchars.domain.usecase

import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import kotlinx.coroutines.flow.Flow

interface CharactersUseCase {
    suspend fun execute(): Flow<Output<List<CharacterEntity>>>
}