package uz.oneid.sdk.auth.model

import uz.oneid.sdk.base.BaseModel
import uz.oneid.sdk.main.User

data class UserInfoResponse(
    val pinfl: String? = null,
    val login: String? = null,
    val email: String? = null,
    val passportData: PassportData? = null
) : BaseModel() {

    fun toUserModel(): User {
        return User(
            pin = pinfl,
            login = login
        )
    }

}