package uz.oneid.sdk.reg.model

import uz.oneid.sdk.base.BaseModel

data class SendSmsRequest(
    val pinfl: String? = null,
    val passSeriaNumber: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
) : BaseModel()