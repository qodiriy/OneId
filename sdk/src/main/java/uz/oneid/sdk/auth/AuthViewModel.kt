package uz.oneid.sdk.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import uz.oneid.sdk.auth.model.AuthRepository
import uz.oneid.sdk.base.BaseViewModel
import uz.oneid.sdk.base.UserModel

class AuthViewModel(private val repository: AuthRepository) : BaseViewModel() {

    private val _data = MutableLiveData<UserModel>()
    val data: LiveData<UserModel> = _data

    private val _state = MutableLiveData<AuthState>()
    val state: LiveData<AuthState> = _state

    private var disposable: Disposable? = null


    fun authWithLoginAndPass(
        login : String, pass : String
    ) {
        disposable?.dispose()
        disposable = repository.authWithLoginAndPass(login,pass)
            .subscribe({
                when(it.code()){
                    200 ->{
                        //_data.postValue(it.body())
                        _state.postValue(AuthState.REGISTERED)
                    }
                    400 ->{
                        Timber.e("400")
                        _state.postValue(AuthState.NOT_REGISTERED)
                    }
                }
            }, {

            })
    }

}