package uz.oneid.sdk.auth

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
                val pinfl = it.data.pinfl
                repository.getUserFromPinfl("Bearer $token", "$pinfl")
            }
            .subscribe({
                when (it.status?.code) {
                    0 -> {
                        //_data.postValue(it.body())
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

}