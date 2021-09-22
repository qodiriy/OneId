package uz.oneid.sdk

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber


object OneIDSDK {


    fun init(application: Application) {
        OneIDDI.start(application)
        Timber.plant(Timber.DebugTree())
    }

    fun startAuth(
        activity: AppCompatActivity, onSuccess: (
            pin: String,
            login: String,
        ) -> (Unit)
    ) {

        val launcher: ActivityResultLauncher<Intent> = activity.registerForActivityResult(
            StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data?.getBundleExtra("data")
                val pin = data?.getString("pin")
                val login = data?.getString("login")

                if (pin != null && login != null)
                    onSuccess(pin, login)
            }
        }

        launcher.launch(Intent(activity, OneIDActivity::class.java))

    }

}