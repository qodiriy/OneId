package uz.oneid.sdk.auth.model

import uz.oneid.sdk.base.BaseModel

data class UserInfoRequest(
    val pinfl: String? = null
) : BaseModel()