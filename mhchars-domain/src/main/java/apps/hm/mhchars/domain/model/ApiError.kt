package apps.hm.mhchars.domain.model

data class ApiError(
    val statusCode: Int = 0,
    val statusMessage: String? = null
)