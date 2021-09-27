package uz.oneid.sdk.recovery.model

import uz.oneid.sdk.base.BaseModel

data class SmsCheckRequest(
    val login: String? = null,
    val smsCode: String? = null
) : BaseModel()