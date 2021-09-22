package uz.oneid.sdk.reg

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import uz.oneid.sdk.reg.model.RegApi
import uz.oneid.sdk.reg.model.RegRepository

object RegModule {

    val reg = module {

        viewModel { RegViewModel(get()) }

        fun provideRegRepository(
            retrofit: Retrofit,
        ): RegRepository {
            val api = retrofit.create(RegApi::class.java)
            return RegRepository(api)
        }
        single { provideRegRepository(get()) }

    }

}