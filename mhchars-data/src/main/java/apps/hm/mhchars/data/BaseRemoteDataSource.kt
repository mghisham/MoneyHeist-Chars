package apps.hm.mhchars.data

import apps.hm.mhchars.domain.model.ApiError
import apps.hm.mhchars.domain.model.Output
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

abstract class BaseRemoteDataSource constructor(
    private val retrofit: Retrofit
) {
    suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Output<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Output.success(result.body())
            } else {
                val errorResponse = parseError(result)
                Output.error(errorResponse?.statusMessage ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Output.error("Unknown Error", null)
        }
    }

    private fun parseError(response: Response<*>): ApiError? {
        val converter =
            retrofit.responseBodyConverter<ApiError>(ApiError::class.java, arrayOfNulls(0))
        return try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            ApiError()
        }
    }
}