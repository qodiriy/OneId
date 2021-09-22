package uz.oneid.sdk.reg.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url
import uz.oneid.sdk.auth.model.AuthRequest
import uz.oneid.sdk.auth.model.AuthResponse
import uz.oneid.sdk.base.BaseResponse

interface RegApi {

    @POST("/api/v1/secure/sms/ask")
    fun sendSms(
        @Body body: SendSmsRequest
    ): Single<BaseResponse<AuthResponse>>

}