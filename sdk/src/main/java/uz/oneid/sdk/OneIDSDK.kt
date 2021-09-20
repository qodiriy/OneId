package uz.oneid.sdk

import android.app.Application
import android.content.Context
import android.content.Intent
import timber.log.Timber

object OneIDSDK {

    fun init(application: Application) {
        OneIDDI.start(application)
    }

    fun startAuth(context: Context) {
        context.startActivity(Intent(context, OneIDActivity::class.java))
    }

}