package uz.oneid.sdk.reg.model

import uz.oneid.sdk.base.BaseModel

data class SignUpRequest(
    val pinfl: String? = null,
    val login: String? = null,
    val password: String? = null,
) : BaseModel()