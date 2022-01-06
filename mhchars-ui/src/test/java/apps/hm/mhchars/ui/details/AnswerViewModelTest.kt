package apps.hm.mhchars.ui.details

import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.ui.BaseViewModelTest
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
class AnswerViewModelTest : BaseViewModelTest() {
    @Mock
    private lateinit var favouriteUseCase: FavouriteUseCase

    private lateinit var answerViewModel: AnswerViewModel

    @Before
    fun setUp() {
        answerViewModel = AnswerViewModel(favouriteUseCase)
    }

    @Test
    fun `Given Quote is setting Favourite then return true`() = runBlockingMainTest {
        //GIVEN
        val flowFavFlag = flowOf(Output.success(true))
        val id = 1
        val isFav = true

        //WHEN
        Mockito.doReturn(flowFavFlag).`when`(favouriteUseCase).setFavourite(isFav, id)
        answerViewModel.setFavourite(id)

        //THEN
        Assert.assertEquals(true, answerViewModel.isFav.value)
        Assert.assertEquals(true, answerViewModel.favChanged.value)
    }

    @Test
    fun `Given Quote is setting Favourite failed then return false`() = runBlockingMainTest {
        //GIVEN
        val flowFavFlag = flowOf(Output.success(false))
        val id = 1
        val isFav = true

        //WHEN
        Mockito.doReturn(flowFavFlag).`when`(favouriteUseCase).setFavourite(isFav, id)
        answerViewModel.setFavourite(id)

        //THEN
        Assert.assertEquals(false, answerViewModel.isFav.value)
        Assert.assertEquals(false, answerViewModel.favChanged.value)
    }

    @Test
    fun `Given Quote id is Favourite then return true`() = runBlockingMainTest {
        //GIVEN
        val flowFavFlag = flowOf(Output.success(true))
        val id = 1

        //WHEN
        Mockito.doReturn(flowFavFlag).`when`(favouriteUseCase).isFavourite(id)
        answerViewModel.isFavourite(id)

        //THEN
        Assert.assertEquals(true, answerViewModel.isFav.value)
    }

    @Test
    fun `Given Quote id is not Favourite then return false`() = runBlockingMainTest {
        //GIVEN
        val flowFavFlag = flowOf(Output.success(false))
        val id = 1

        //WHEN
        Mockito.doReturn(flowFavFlag).`when`(favouriteUseCase).isFavourite(id)
        answerViewModel.isFavourite(id)

        //THEN
        Assert.assertEquals(false, answerViewModel.isFav.value)
    }
}