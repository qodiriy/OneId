package uz.oneid.sdk.auth.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthApi {

    @POST
    fun withLoginAndPass(
        @Url url: String,
        @Body body: AuthRequest
    ): Single<Response<AuthResponse>>


}