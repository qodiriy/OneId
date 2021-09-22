package uz.oneid.sdk.auth

import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import uz.oneid.sdk.auth.model.AuthRepository
import uz.oneid.sdk.base.BaseViewModel
import uz.oneid.sdk.base.UserModel

class AuthViewModel(private val repository: AuthRepository) : BaseViewModel() {


    private val _state = MutableLiveData<AuthState>()
    val state: LiveData<AuthState> = _state

    private var disposable: Disposable? = null


    fun authWithLoginAndPass(
        login: String, pass: String
    ): LiveData<UserModel> {

        val result = MutableLiveData<UserModel>()

        disposable?.dispose()
        disposable = repository.authWithLoginAndPass(login, pass)
            .flatMap {
                val token = it.data.accessToken
                val pinfl = it.data.pinfl ?: ""
                val pin = pinfl + pinfl.take(4) + pinfl.takeLast(4)
                Timber.e("Pin : $pin nextline")
                val hash = pin.toBase64()
                Timber.e("Hash : $hash nextline")
                repository.getUserFromPinfl("Bearer $token", hash)
            }
            .subscribe({
                when (it.status?.code) {
                    0 -> {
                        it.data.toUserModel().let { r ->
                            result.postValue(r)
                        }
                        _state.postValue(AuthState.REGISTERED)
                    }
                    1 -> {
                        Timber.e("400")
                        _state.postValue(AuthState.NOT_REGISTERED)
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