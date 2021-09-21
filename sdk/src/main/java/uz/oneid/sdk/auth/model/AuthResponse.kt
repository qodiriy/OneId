package uz.oneid.sdk.auth.model

import uz.oneid.sdk.base.BaseModel

data class AuthResponse(
    val pinfl: String? = null,
    val accessToken : String? = null,
    val refreshToken : String? = null

) : BaseModel()