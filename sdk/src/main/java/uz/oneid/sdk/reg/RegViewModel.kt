package uz.oneid.sdk.reg

import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import uz.oneid.sdk.auth.AuthState
import uz.oneid.sdk.base.BaseViewModel
import uz.oneid.sdk.base.UserModel
import uz.oneid.sdk.reg.model.RegRepository

class RegViewModel(private val repository: RegRepository) : BaseViewModel() {


    private val _state = MutableLiveData<RegState>()
    val state: LiveData<RegState> = _state

    private var disposable: Disposable? = null


    fun sendSms(
        pin: String, doc: String, email: String, phone: String
    ): LiveData<Boolean> {

        val result = MutableLiveData<Boolean>()

        disposable?.dispose()
        disposable = repository.sendSms(pin, doc, email, phone)
            .subscribe({
                when (it.status?.code) {
                    0 -> {
                        result.postValue(true)
                    }
                    else -> {
                        result.postValue(false)
                    }
                }
            }, {

            })

        return result

    }

    fun checkSms(
        pin: String, code: String
    ): LiveData<Boolean> {

        val result = MutableLiveData<Boolean>()

        disposable?.dispose()
        disposable = repository.checkSms(pin, code)
            .subscribe({
                when (it.status?.code) {
                    0 -> {
                        result.postValue(true)
                    }
                    else -> {
                        result.postValue(false)
                    }
                }
            }, {

            })

        return result

    }

    fun signUp(
        pin: String, login: String, password: String
    ): LiveData<UserModel> {

        val result = MutableLiveData<UserModel>()

        disposable?.dispose()
        disposable = repository.signUp(pin, login, password)
            .flatMap {
                val token = it.data.accessToken
                val pinfl = it.data.pinfl ?: ""
                val p = pinfl + pinfl.take(4) + pinfl.takeLast(4)
                Timber.e("Pin : $pin nextline")
                val hash = p.toBase64()
                Timber.e("Hash : $hash nextline")
                repository.getUserFromPinfl("Bearer $token", hash)
            }
            .subscribe({
                when (it.status?.code) {
                    0 -> {
                        it.data.toUserModel().let { r ->
                            result.postValue(r)
                        }
                        _state.postValue(RegState.REGISTERED)
                    }
                    1 -> {
                        Timber.e("400")
                        _state.postValue(RegState.NOT_REGISTERED)
                    }
                }
            }, {

            })

        return result

    }

    private fun String.toBase64(): String {
        return Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)
    }


}