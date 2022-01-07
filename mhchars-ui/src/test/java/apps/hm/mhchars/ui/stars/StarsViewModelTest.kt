package apps.hm.mhchars.ui.stars

import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.domain.usecase.CharactersUseCase
import apps.hm.mhchars.ui.BaseViewModelTest
import apps.hm.mhchars.ui.getDummyCharacters
import apps.hm.mhchars.ui.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StarsViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var charactersUseCase: CharactersUseCase
    private lateinit var starsViewModel: StarsViewModel

    @Before
    fun setUp() {
        starsViewModel = StarsViewModel(charactersUseCase)
    }

    @Test
    fun `Given Characters when fetchCharacters should return Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(getDummyCharacters()))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(charactersUseCase).execute()
        starsViewModel.fetchStars()

        //THEN
        assert(1 == starsViewModel.starsList.value?.data?.size)
    }
}