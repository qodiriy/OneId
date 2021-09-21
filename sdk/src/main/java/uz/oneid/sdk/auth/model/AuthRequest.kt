package uz.oneid.sdk.auth.model

import uz.oneid.sdk.base.BaseModel

data class AuthRequest(
    val login : String? = null,
    val password : String? = null
) : BaseModel()