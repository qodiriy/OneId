package uz.oneid.sdk.auth.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import uz.oneid.sdk.base.BaseRepository


class AuthRepository(private val api: AuthApi) : BaseRepository() {

    fun authWithLoginAndPass(pin: String, document: String): Single<Response<AuthResponse>> {
        return api.withLoginAndPass(
            "http://172.16.30.113:8080/api/v2/user/signIn",
            AuthRequest(
                pin = pin.toLongOrNull()?:0,
                document = document
            )
        )
    }

}