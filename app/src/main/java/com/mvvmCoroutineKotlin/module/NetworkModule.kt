package com.mvvmCoroutineKotlin.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mvvmCoroutineKotlin.networking.NetworkRequest
import com.mvvmCoroutineKotlin.networking.NetworkService
import com.mvvmCoroutineKotlin.roomDB.DBHelper
import com.mvvmCoroutineKotlin.scopes.PerApplication
import com.mvvmCoroutineKotlin.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

@Module(includes = [AppDBModule::class])
class NetworkModule {

    @Provides
    @PerApplication
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @PerApplication
    fun providesCacheFile(context: Context): File = File(context.cacheDir, "responses")

    @Provides
    @PerApplication
    fun providesOkHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .cache(cache)
        .build()

    @Provides
    @PerApplication
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    @Provides
    @PerApplication
    fun providesNetworkRequest(retrofit: Retrofit): NetworkRequest = retrofit.create(NetworkRequest::class.java)

    @Provides
    @PerApplication
    fun providesNetworkService(networkRequest: NetworkRequest): NetworkService =
        NetworkService(networkRequest)

    @Provides
    @PerApplication
    fun providesCache(cacheFile: File): Cache {
        lateinit var cache: Cache
        try {
            cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cache
    }

    @Provides
    @PerApplication
    fun providesInterceptor(dbHelper: DBHelper): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()

            // Customize the request
            val request = original.newBuilder()
                .addHeader(Constants.API_KEY, Constants.HVALUE)
                .header("Content-Type", "application/json")
                .removeHeader("Pragma")
                .header("Cache-Control", String.format(Locale.getDefault(), "max-age=%d", Constants.CACHE_TIME))
                .build()
            val response = chain.proceed(request)
            //json response --> response.body().string();
            // Customize or return the response
            response
        }
    }
}
