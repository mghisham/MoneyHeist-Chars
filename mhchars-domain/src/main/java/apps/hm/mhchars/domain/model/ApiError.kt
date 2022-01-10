package apps.hm.mhchars.domain.model

/**
 * Default Error Entity class for Api service errors
 * @param statusCode the status code of service
 * @param statusMessage the message from api service
 */
data class ApiError(
    val statusCode: Int = 0,
    val statusMessage: String? = null
)