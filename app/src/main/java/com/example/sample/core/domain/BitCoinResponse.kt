package com.example.sample.core.domain

import com.example.sample.base.extension.empty

data class BitCoinResponse(
	val bpi: Bpi,
	val chartName: String,
	val disclaimer: String
//    val time: Time
) {
	companion object {
		fun empty() = BitCoinResponse(
			Bpi(EUR.empty(), GBP.empty(), USD.empty()),
			String.empty(),
			String.empty()
		)
	}
}