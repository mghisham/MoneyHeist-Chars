package apps.hm.mhchars.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import apps.hm.mhchars.domain.getDummyCharacters
import apps.hm.mhchars.data.services.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharsRemoteDataSourceTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var profService: ApiService

    private lateinit var charsRemoteDataSource: CharsRemoteDataSource

    @Before
    fun setUp() {
        charsRemoteDataSource = CharsRemoteDataSource(profService, retrofit)
    }

    @Test
    fun `Given Characters When fetchCharacters returns Success`() = runBlocking {
        //GIVEN
        val givenCharacters = getDummyCharacters()
        Mockito.`when`(profService.getCharacters()).thenReturn(Response.success(givenCharacters))
        //WHEN
        val fetchedCharacters = charsRemoteDataSource.fetchCharacters()
        //THEN
        assert(fetchedCharacters.data?.size == givenCharacters.size)
    }

    @Test
    fun `Given Characters When fetchCharacters returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(profService.getCharacters()).thenThrow(mockitoException)
        //WHEN
        val fetchedCharacters = charsRemoteDataSource.fetchCharacters()
        //THEN
        assert(fetchedCharacters.message == "Unknown Error")
    }

    @Test
    fun `Given Characters When fetchCharacters returns Server Error`() = runBlocking {
        //GIVEN
        Mockito.`when`(profService.getCharacters())
            .thenReturn(Response.error(500, "".toResponseBody()))
        //WHEN
        val fetchedCharacters = charsRemoteDataSource.fetchCharacters()
        //THEN
        assert(fetchedCharacters.message == "Unknown Error")
    }
}