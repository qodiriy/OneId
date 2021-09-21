package uz.oneid.sdk.auth.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url
import uz.oneid.sdk.base.BaseResponse

interface AuthApi {

    @POST("/api/v1/secure/signIn")
    fun withLoginAndPass(
        @Body body: AuthRequest
    ): Single<BaseResponse<AuthResponse>>


    @POST("/api/v1/secure/getUser")
    fun getUserInfo(
        @Header("Authorisation") token: String,
        @Body body: UserInfoRequest
    ): Single<BaseResponse<AuthResponse>>

}