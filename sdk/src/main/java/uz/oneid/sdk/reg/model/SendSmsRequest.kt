package uz.oneid.sdk.reg.model

import uz.oneid.sdk.base.BaseModel

data class SendSmsRequest(
    val pinfl: String? = null,
    val passSerialNumber: String? = null,
    val phone: String? = null,
    val email: String? = null,
) : BaseModel()