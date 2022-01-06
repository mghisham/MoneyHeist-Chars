package apps.hm.mhchars.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import apps.hm.mhchars.domain.getDummyCharacters
import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.domain.repository.CharsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterUseCaseImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var charsRepository: CharsRepository
    private lateinit var characterUseCase: CharactersUseCaseImpl

    @Before
    fun setUp() {
        characterUseCase = CharactersUseCaseImpl(charsRepository)
    }

    @Test
    fun `Given Characters When UseCase fetchCharacters returns Success`() = runBlocking {
        //GIVEN
        val inputFlow = flowOf(Output.success(getDummyCharacters()))
        Mockito.`when`(charsRepository.fetchCharacters()).thenReturn(inputFlow)
        //WHEN
        val output = characterUseCase.execute().toList()
        //THEN
        assert(output[0].data?.size == 1)
    }

}