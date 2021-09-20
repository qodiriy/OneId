package uz.oneid.sdk

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object AppModule {

    val app = module {


        fun provideCache(application: Application): Cache {
            val cacheSize = 10 * 1024 * 1024
            return Cache(application.cacheDir, cacheSize.toLong())
        }
        single { provideCache(androidApplication()) }

        fun provideChuck(application: Application): ChuckInterceptor {
            return ChuckInterceptor(application)
        }
        single { provideChuck(androidApplication()) }

        fun provideDeviceHttpClient( cache: Cache,chuck : ChuckInterceptor): OkHttpClient {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()

            }
            )

            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            val okHttpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor (chuck)
                .sslSocketFactory( sslContext.socketFactory,  trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true }
                .addNetworkInterceptor { chain ->
                    val request: Request =
                        chain.request()
                            .newBuilder()
                            .build()
                    chain.proceed(request)
                }
                .cache(cache)

            return okHttpClientBuilder.build()
        }

        single{ provideDeviceHttpClient(get(), get()) }


        fun provideGSON(): Gson {
            return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        }
        single { provideGSON() }


        fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(factory))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client).build()
        }

        single { provideRetrofit(get(), get()) }


    }


}