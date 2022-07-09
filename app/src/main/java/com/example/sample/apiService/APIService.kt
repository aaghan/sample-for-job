package com.example.sample.apiService

import com.example.sample.core.domain.BitCoinResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIService @Inject constructor(retrofit: Retrofit) : ApiInterface {
	private val apiInterface by lazy { retrofit.create(ApiInterface::class.java) }
	override fun getBitCoinResponse(): Call<BitCoinResponse> {
		return apiInterface.bitCoinResponse
	}
	
}