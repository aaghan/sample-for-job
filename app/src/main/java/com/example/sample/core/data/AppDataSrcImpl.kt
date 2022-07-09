package com.example.sample.core.data

import android.content.Context
import com.example.sample.apiService.APIService

import com.example.sample.base.NetworkHandler
import com.example.sample.base.NetworkUtilsAPI
import com.example.sample.base.exception.Failure
import com.example.sample.base.functional.Either
import com.example.sample.core.domain.BitCoinResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.CacheResponse
import javax.inject.Inject

class AppDataSrcImpl
@Inject constructor(
	private val networkHandler: NetworkHandler,
	private val service: APIService,
	@ApplicationContext private var context: Context,
	
	) : AppCoreRepo {
	
	override fun getBitCoinResponse(): Either<Failure, BitCoinResponse> {
		
		return when (networkHandler.isNetworkConnected) {
			true -> {
				NetworkUtilsAPI.request(
					call = service.getBitCoinResponse(),
					transform = { it },
					default = BitCoinResponse.empty()
				)
			}
			false, null -> {
				Either.Left(Failure.NetworkConnection)
				
			}
		}
		
	}
	
	
}