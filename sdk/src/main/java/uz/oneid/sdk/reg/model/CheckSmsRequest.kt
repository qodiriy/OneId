package uz.oneid.sdk.reg.model

import uz.oneid.sdk.base.BaseModel

data class CheckSmsRequest(
    val pinfl: String? = null,
    val smsCode: String? = null,
) : BaseModel()