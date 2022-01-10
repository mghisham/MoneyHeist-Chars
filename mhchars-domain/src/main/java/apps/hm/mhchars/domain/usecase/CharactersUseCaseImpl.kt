package apps.hm.mhchars.domain.usecase

import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.domain.repository.CharsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of Characters UseCase
 * @param charsRepository the chars repository object
 */
internal class CharactersUseCaseImpl @Inject constructor(private val charsRepository: CharsRepository) :
    CharactersUseCase {

    override suspend fun execute(): Flow<Output<List<CharacterEntity>>> {
        return charsRepository.fetchCharacters()
    }
}