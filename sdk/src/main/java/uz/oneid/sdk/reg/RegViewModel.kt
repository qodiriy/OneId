package uz.oneid.sdk.reg

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
        pin : String,doc : String, email: String, phone: String
    ): LiveData<UserModel> {

        val result = MutableLiveData<UserModel>()

        disposable?.dispose()
        disposable = repository.sendSms(pin, doc, email, phone)
                .subscribe({
                when (it.status?.code) {
//                    0 -> {
//                        it.data.toUserModel().let { r ->
//                            result.postValue(r)
//                        }
//                        _state.postValue(AuthState.REGISTERED)
//                    }
//                    1 -> {
//                        Timber.e("400")
//                        _state.postValue(AuthState.NOT_REGISTERED)
//                    }
                }
            }, {

            })

        return result

    }

}