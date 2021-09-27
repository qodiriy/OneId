package uz.oneid.sdk.recovery.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST
import uz.oneid.sdk.base.BaseResponse

interface RecoveryApi {

    @POST("/api/v1/profile/resetPasswordByPinfl")
    fun resetPassword(
        @Body body: ResetPasswordRequest
    ): Single<BaseResponse<ResetPasswordResponse>>


    @POST("/api/v1/profile/smsCheck/RecoveryPassword")
    fun smsCheck(
        @Body body: SmsCheckRequest
    ): Single<BaseResponse<SmsCheckResponse>>

    @POST("/api/v1/profile/setRecoveryPassword")
    fun recoverPassword(
        @Body body: RecoverPasswordRequest
    ): Single<BaseResponse<RecoverPasswordResponse>>


}