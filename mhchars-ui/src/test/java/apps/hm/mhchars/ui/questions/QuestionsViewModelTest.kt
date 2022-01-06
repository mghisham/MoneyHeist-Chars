package apps.hm.mhchars.ui.questions

import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.domain.usecase.CharactersUseCase
import apps.hm.mhchars.ui.BaseViewModelTest
import apps.hm.mhchars.ui.getDummyFavQns
import apps.hm.mhchars.ui.getDummyQuestionsEntity
import apps.hm.mhchars.ui.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class QuestionsViewModelTest : BaseViewModelTest() {
    @Mock
    private lateinit var charactersUseCase: CharactersUseCase

    private lateinit var questionsViewModel: QuestionsViewModel

    @Before
    fun setUp() {
        questionsViewModel = QuestionsViewModel(charactersUseCase)
    }

    @Test
    fun `Given Questions when fetchQuestions should return Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(getDummyQuestionsEntity()))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(charactersUseCase).fetchLevelQuestionsCached(1)
        questionsViewModel.fetchLevelQuestionsCached(1)

        //THEN
        Assert.assertEquals(1, questionsViewModel.questionList.value?.data?.size)
    }

    @Test
    fun `Given Questions when fetchFavouriteQuestions should return Success`() =
        runBlockingMainTest {
            //GIVEN
            val flowQuestions = flowOf(Output.success(getDummyFavQns()))

            //WHEN
            Mockito.doReturn(flowQuestions).`when`(charactersUseCase).fetchFavouriteQuestionsCached()
            questionsViewModel.fetchFavouriteQuestions()

            //THEN
            Assert.assertEquals(1, questionsViewModel.questionList.value?.data?.size)
        }

    @Test
    fun `Given Questions when fetchLangCategoryQuestions should return Success`() =
        runBlockingMainTest {
            //GIVEN
            val flowQuestions = flowOf(Output.success(getDummyQuestionsEntity()))

            //WHEN
            Mockito.doReturn(flowQuestions).`when`(charactersUseCase)
                .fetchLangCategoryQuestionsCached(1)
            questionsViewModel.fetchLangCategoryQuestions(1)

            //THEN
            Assert.assertEquals(1, questionsViewModel.questionList.value?.data?.size)
        }
}