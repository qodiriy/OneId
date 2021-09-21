package uz.oneid.sdk.base

data class BaseResponse<T>(
    val data: T,
    val status: ResponseStatus? = null,
) : BaseModel()