package apps.hm.mhchars.ui.dashboard

import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.domain.usecase.CharactersUseCase
import apps.hm.mhchars.ui.BaseViewModelTest
import apps.hm.mhchars.ui.getDummyQuestionsEntity
import apps.hm.mhchars.ui.getDummyQuoteEntity
import apps.hm.mhchars.ui.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var quoteUseCase: QuoteUseCase

    @Mock
    private lateinit var charactersUseCase: CharactersUseCase

    private lateinit var dashboardViewModel: DashboardViewModel

    @Before
    fun setUp() {
        dashboardViewModel = DashboardViewModel(quoteUseCase, charactersUseCase)
    }

    @Test
    fun `Given questions When fetchQuestions returns Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(getDummyQuestionsEntity()))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(charactersUseCase).fetchQuestionsCached()
        dashboardViewModel.fetchQuestions()

        //THEN
        assertEquals(1, dashboardViewModel.questionList.value?.data?.size)
        assertEquals(1, dashboardViewModel.count.value?.first)
        assertEquals(0, dashboardViewModel.favCount.value)
    }

    @Test
    fun `Given Quote When getRandomQuote returns Success`() = runBlockingMainTest {
        //GIVEN
        val data = getDummyQuoteEntity()
        val flowQuote = flowOf(Output.success(data))

        //WHEN
        Mockito.doReturn(flowQuote).`when`(quoteUseCase).getRandomQuote()
        dashboardViewModel.getRandomQuote()

        //THEN
        assertEquals(dashboardViewModel.quote.value, data)
    }
}