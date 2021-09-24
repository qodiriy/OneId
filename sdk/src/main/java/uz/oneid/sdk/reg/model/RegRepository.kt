package uz.oneid.sdk.reg.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import timber.log.Timber
import uz.oneid.sdk.auth.model.AuthRequest
import uz.oneid.sdk.auth.model.AuthResponse
import uz.oneid.sdk.auth.model.UserInfoRequest
import uz.oneid.sdk.auth.model.UserInfoResponse
import uz.oneid.sdk.base.BaseRepository
import uz.oneid.sdk.base.BaseResponse
import kotlin.math.log


class RegRepository(private val api: RegApi) : BaseRepository() {

    fun sendSms(
        pin: String,
        doc: String,
        email: String,
        phone: String
    ): Single<BaseResponse<SendSmsResponse>> {
        return api.sendSms(
            SendSmsRequest(
                pinfl = pin,
                passSeriaNumber = doc,
                email = email,
                phoneNumber = phone
            )
        )
    }

    fun checkSms(
        pin: String,
        smsCode: String
    ): Single<BaseResponse<CheckSmsResponse>> {
        return api.checkSms(
            CheckSmsRequest(
                pinfl = pin,
                smsCode = smsCode,
            )
        )
    }

    fun signUp(
        pin: String,
        login: String,
        password: String
    ): Single<BaseResponse<SignUpResponse>> {
        return api.signUp(
            SignUpRequest(
                pinfl = pin,
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