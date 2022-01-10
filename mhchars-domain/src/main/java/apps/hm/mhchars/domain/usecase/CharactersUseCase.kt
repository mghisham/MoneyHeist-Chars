package apps.hm.mhchars.domain.usecase

import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import kotlinx.coroutines.flow.Flow

/**
 * Interface of Characters UseCase to expose to ui module
 */
interface CharactersUseCase {
    /**
     * UseCase Method to fetch the characters from Data Layer
     */
    suspend fun execute(): Flow<Output<List<CharacterEntity>>>
}