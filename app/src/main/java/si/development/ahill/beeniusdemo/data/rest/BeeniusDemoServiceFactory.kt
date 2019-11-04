package si.development.ahill.beeniusdemo.data.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import si.development.ahill.beeniusdemo.BuildConfig
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY as LOG_LEVEL_BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE as LOG_LEVEL_NONE

/**
 * Created by Andraž Hribar on 4. 11. 2019.
 * andraz.hribar@gmail.com
 */
class BeeniusDemoServiceFactory {

    private val retrofit: Retrofit = createRetrofit()

    fun <T> create(service: Class<T>): T =
        retrofit.create(service)

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(createGsonConverterFactory())
            .client(createOkHttpClient())
            .build()

    private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(TIMEOUT_SECONDS_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS_WRITE, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS_CONNECT, TimeUnit.SECONDS)
            .addInterceptor(createLoggingInterceptor())
            .build()

    private fun createGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    private fun createLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .apply {
                level = if (BuildConfig.DEBUG) LOG_LEVEL_BODY else LOG_LEVEL_NONE
            }
}