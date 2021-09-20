package uz.oneid.sdk.auth.model

import uz.oneid.sdk.base.BaseModel


data class AuthRequest(
    val pin: Long? = null,
    val document: String? = null,
    val login : String? = null,
    val password : String? = null
) : BaseModel()