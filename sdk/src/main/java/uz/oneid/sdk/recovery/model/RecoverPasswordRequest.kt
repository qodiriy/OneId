package uz.oneid.sdk.recovery.model

import uz.oneid.sdk.base.BaseModel

data class RecoverPasswordRequest(
    val login: String? = null,
    val newPassword: String? = null
) : BaseModel()