package uz.oneid.sdk.auth.model

import uz.oneid.sdk.base.BaseModel
import uz.oneid.sdk.base.UserModel
import kotlin.math.log

data class UserInfoResponse(
    val pinfl: String? = null,
    val login: String? = null,
    val email: String? = null,
    val passportData: PassportData? = null
) : BaseModel() {

    fun toUserModel(): UserModel {
        return UserModel(
            pin = pinfl,
            login = login
        )
    }

}