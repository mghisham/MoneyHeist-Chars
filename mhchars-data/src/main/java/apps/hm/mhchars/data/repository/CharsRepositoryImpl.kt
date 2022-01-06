package apps.hm.mhchars.data.repository


import apps.hm.mhchars.data.remote.CharsRemoteDataSource
import apps.hm.mhchars.domain.model.CharacterEntity
import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.domain.repository.CharsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharsRepositoryImpl @Inject constructor(
    private val charsRemoteDataSource: CharsRemoteDataSource
) : CharsRepository {
    override suspend fun fetchCharacters(): Flow<Output<List<CharacterEntity>>> {
        return flow {
            emit(Output.loading())
            val result = charsRemoteDataSource.fetchCharacters()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}