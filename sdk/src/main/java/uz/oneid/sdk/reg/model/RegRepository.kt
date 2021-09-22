package uz.oneid.sdk.reg.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import timber.log.Timber
import uz.oneid.sdk.auth.model.AuthRequest
import uz.oneid.sdk.auth.model.AuthResponse
import uz.oneid.sdk.base.BaseRepository
import uz.oneid.sdk.base.BaseResponse


class RegRepository(private val api: RegApi) : BaseRepository() {

    fun sendSms(
        pin: String,
        doc: String,
        email: String,
        phone: String
    ): Single<BaseResponse<AuthResponse>> {
        return api.sendSms(
            SendSmsRequest(
                pinfl = pin,
                passSerialNumber = doc,
                email = email,
                phone = phone
            )
        )
    }

}