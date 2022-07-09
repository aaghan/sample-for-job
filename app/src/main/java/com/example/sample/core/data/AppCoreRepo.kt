package com.example.sample.core.data

import com.example.sample.base.exception.Failure
import com.example.sample.base.functional.Either
import com.example.sample.core.domain.BitCoinResponse

interface AppCoreRepo {
	fun getBitCoinResponse(): Either<Failure, BitCoinResponse>
}