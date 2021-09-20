package uz.egov.oneid

import android.app.Application
import uz.oneid.sdk.OneIDSDK

class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        OneIDSDK.init(this)
    }

}