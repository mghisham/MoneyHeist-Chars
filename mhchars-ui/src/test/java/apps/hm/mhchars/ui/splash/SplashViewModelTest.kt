package apps.hm.mhchars.ui.splash

import apps.hm.mhchars.domain.model.Output
import apps.hm.mhchars.ui.BaseViewModelTest
import apps.hm.mhchars.ui.observeForTesting
import apps.hm.mhchars.ui.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest : BaseViewModelTest() {

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setUp() {
        splashViewModel = SplashViewModel()
    }

    @Test
    fun `Given output When load returns Success`() = runBlockingMainTest {
        //WHEN
        splashViewModel.load()

        //THEN
        splashViewModel.isOk.observeForTesting {
            assert(splashViewModel.isOk.value?.status == Output.Status.LOADING)
        }
    }
}