package uz.oneid.sdk.auth.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import uz.oneid.sdk.base.BaseRepository
import uz.oneid.sdk.base.BaseResponse


class AuthRepository(private val api: AuthApi) : BaseRepository() {

    fun authWithLoginAndPass(login: String, password: String): Single<BaseResponse<AuthResponse>> {
        return api.withLoginAndPass(
            AuthRequest(
                login = login,
                password = password
            )
        )
    }

    fun getUserFromPinfl(token: String, pinfl: String): Single<BaseResponse<AuthResponse>> {
        return api.getUserInfo(
            UserInfoRequest(
                pinfl = pinfl
            )
        )
    }

}