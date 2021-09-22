package uz.oneid.sdk.auth.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import timber.log.Timber
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

    fun getUserFromPinfl(token: String, pinfl: String): Single<BaseResponse<UserInfoResponse>> {
        val request =  UserInfoRequest(
            pinfl = pinfl
        )

        Timber.e("Request : ${request.pinfl} nextline")

        return api.getUserInfo(
            token,
            request
        )
    }

}