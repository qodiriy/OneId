package uz.oneid.sdk.auth

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import uz.oneid.sdk.auth.model.AuthApi
import uz.oneid.sdk.auth.model.AuthRepository

object AuthModule {

    val auth = module {

        viewModel { AuthViewModel(get()) }

        fun provideAuthRepository(
            retrofit: Retrofit,
        ): AuthRepository {
            val api = retrofit.create(AuthApi::class.java)
            return AuthRepository(api)
        }
        single { provideAuthRepository(get()) }

    }

}