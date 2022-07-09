package com.example.sample.main.di

import android.content.Context
import android.content.SharedPreferences
import com.example.sample.BuildConfig
import com.example.sample.core.data.AppCoreRepo
import com.example.sample.core.data.AppDataSrcImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
	
	@Qualifier
	@Retention(AnnotationRetention.BINARY)
	annotation class SharedPreferenceApp
	
	@Qualifier
	@Retention(AnnotationRetention.BINARY)
	annotation class MacAddressAPP
	
	@Provides
	@Singleton
	fun provideAppCoreRepo(dataSource: AppDataSrcImpl): AppCoreRepo = dataSource
	
	@SharedPreferenceApp
	@Provides
	fun getSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
		appContext.getSharedPreferences("mSharedPrefs", Context.MODE_PRIVATE)
	
	@Singleton
	@Provides
	fun getClientBuilder(@ApplicationContext applicationContext: Context): OkHttpClient.Builder {
		
		val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
			.connectTimeout(40, TimeUnit.SECONDS)
			.readTimeout(40, TimeUnit.SECONDS)
			.writeTimeout(40, TimeUnit.SECONDS)
			.retryOnConnectionFailure(true)
			.followRedirects(true)
			.followSslRedirects(true)
		
		
		//logging interceptor
		if (BuildConfig.DEBUG) {
			val loggingInterceptor =
				HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
			okHttpClientBuilder.addInterceptor(loggingInterceptor)
		}
		
		
		//Header Interceptor
		okHttpClientBuilder.addInterceptor { chain ->
			var request = chain.request()
			request = request.newBuilder()
				.addHeader("Content-Type", "application/json")
				?.addHeader("Accept", "application/json")
				?.addHeader("Cache-Control", "no-cache")
				?.build()
			chain.proceed(request)
		}
		return okHttpClientBuilder
	}
	
	private lateinit var client: OkHttpClient
	private fun getClient(@ApplicationContext appContext: Context): OkHttpClient {
		if (!::client.isInitialized) {
			val clientBuilder = getClientBuilder(appContext)
			client = clientBuilder.build()
		}
		
		return client
	}
	
	@Provides
	open fun provideRetrofit(@ApplicationContext appContext: Context): Retrofit {
		
		val BASE_URL = "https://api.coindesk.com"
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
//            .client(OkClient()!!) // client to support ssl certificate error
			.client(getClient(appContext))
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
	
}
