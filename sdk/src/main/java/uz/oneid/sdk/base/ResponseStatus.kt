package uz.oneid.sdk.base

data class ResponseStatus(
    val code: Int,
    val message: String? = null
) : BaseModel()