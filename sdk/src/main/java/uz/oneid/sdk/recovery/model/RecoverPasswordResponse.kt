package uz.oneid.sdk.recovery.model

import uz.oneid.sdk.base.BaseModel

data class RecoverPasswordResponse(
    val pinfl: String? = null,
    val passSeriaNumber: String? = null
) : BaseModel()