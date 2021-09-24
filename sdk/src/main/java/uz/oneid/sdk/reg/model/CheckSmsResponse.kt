package uz.oneid.sdk.reg.model

import uz.oneid.sdk.base.BaseModel

data class CheckSmsResponse(
    val pinfl: String? = null,
    val limit : Int? = null
) : BaseModel()