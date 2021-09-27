package uz.oneid.sdk.recovery.model

import io.reactivex.rxjava3.core.Single
import uz.oneid.sdk.base.BaseRepository
import uz.oneid.sdk.base.BaseResponse


class RecoveryRepository(private val api: RecoveryApi) : BaseRepository() {

    fun resetPassword(
        pin: String,
        doc: String
    ): Single<BaseResponse<ResetPasswordResponse>> {
        return api.resetPassword(
            ResetPasswordRequest(
                pinfl = pin,
                passSeriaNumber = doc
            )
        )
    }

    fun smsCheck(
        login : String,
        smsCode: String
    ): Single<BaseResponse<SmsCheckResponse>> {
        return api.smsCheck(
            SmsCheckRequest(
                login = login,
                smsCode = smsCode,
            )
        )
    }

    fun recoverPassword(
        login: String,
        password: String
    ): Single<BaseResponse<RecoverPasswordResponse>> {
        return api.recoverPassword(
            RecoverPasswordRequest(
                login = login,
                newPassword=password
            )
        )
    }

//
//    fun signUp(
//        pin: String,
//        login: String,
//        password: String
//    ): Single<BaseResponse<SignUpResponse>> {
//        return api.signUp(
//            SignUpRequest(
//                pinfl = pin,
//                login = login,
//                password = password
//            )
//        )
//    }
//
//    fun getUserFromPinfl(token: String, pinfl: String): Single<BaseResponse<UserInfoResponse>> {
//        val request =  UserInfoRequest(
//            pinfl = pinfl
//        )
//
//        Timber.e("Request : ${request.pinfl} nextline")
//
//        return api.getUserInfo(
//            token,
//            request
//        )
//    }

}