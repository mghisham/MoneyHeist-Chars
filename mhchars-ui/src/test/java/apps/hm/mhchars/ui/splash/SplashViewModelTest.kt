package apps.hm.mhchars.ui.splash

import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.domain.usecase.CharactersUseCase
import apps.hm.mhchars.ui.BaseViewModelTest
import apps.hm.mhchars.ui.getDummyQuestionsEntity
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
class SplashViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var charactersUseCase: CharactersUseCase

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setUp() {
        splashViewModel = SplashViewModel(charactersUseCase)
    }

    @Test
    fun `Given questions When fetchQuestions returns Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(true))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(charactersUseCase).fetchQuestions()
        splashViewModel.fetchQuestions()

        //THEN
        assert(splashViewModel.questionOk.value?.data == true)
    }

    @Test
    fun `Given questions When fetchCachedQuestions returns Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(getDummyQuestionsEntity()))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(charactersUseCase).fetchQuestionsCached()
        splashViewModel.fetchCachedQuestions()

        //THEN
        assert(splashViewModel.localQuestionOk.value == true)
    }
}