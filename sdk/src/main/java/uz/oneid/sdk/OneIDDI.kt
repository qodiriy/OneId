package uz.oneid.sdk

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.logger.Level
import timber.log.Timber
import uz.oneid.sdk.auth.AuthModule


object OneIDDI {

    private var application: Application? = null

    private val modules = listOf(
        AppModule.app,
        AuthModule.auth
    )

    fun start(app: Application) {
        application = app
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(app)
            modules(modules)
        }
    }

    fun reload() {
        Timber.e("reload")

        unloadKoinModules(modules)
        loadKoinModules(modules)
    }



}