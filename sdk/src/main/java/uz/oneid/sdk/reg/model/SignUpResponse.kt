package uz.oneid.sdk.reg.model

import uz.oneid.sdk.base.BaseModel

data class SignUpResponse(
    val pinfl: String? = null,
    val accessToken : String? = null,
    val refreshToken : String? = null
) : BaseModel()