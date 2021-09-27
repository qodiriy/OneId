package uz.oneid.sdk.recovery

import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import uz.oneid.sdk.base.BaseViewModel
import uz.oneid.sdk.main.User
import uz.oneid.sdk.recovery.model.RecoveryRepository
import uz.oneid.sdk.recovery.model.RecoveryState

class RecoveryViewModel(private val repository: RecoveryRepository) : BaseViewModel() {


    private val _state = MutableLiveData<RecoveryState>()
    val state: LiveData<RecoveryState> = _state

    private var disposable: Disposable? = null


    fun resetPassword(
        pin: String, doc: String
    ): LiveData<Boolean> {

        val result = MutableLiveData<Boolean>()

        disposable?.dispose()
        disposable = repository.resetPassword(pin, doc)
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
        login: String, code: String
    ): LiveData<Boolean> {

        val result = MutableLiveData<Boolean>()

        disposable?.dispose()
        disposable = repository.smsCheck(login, code)
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

    fun recoverPassword(
        login: String, password: String
    ): LiveData<User> {

        val result = MutableLiveData<User>()

        disposable?.dispose()
        disposable = repository.recoverPassword(login, password)
            .subscribe({
                when (it.status?.code) {
                    0 -> {

                        _state.postValue(RecoveryState.REGISTERED)
                    }
                    1 -> {
                        Timber.e("400")
                        _state.postValue(RecoveryState.NOT_REGISTERED)
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