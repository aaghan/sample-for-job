package com.example.sample.base
/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.example.sample.base.exception.Failure
import com.example.sample.base.functional.Either
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Response

open class NetworkUtilsAPI {
	companion object {
		val TAG = NetworkUtilsAPI::class.java.canonicalName
		fun <T, R> request(
			call: Call<T>,
			transform: (T) -> R,
			default: T
		): Either<Failure, R> {
			var responseCode = ""
			return try {
				val response: Response<T> = call.execute()
				responseCode = response.code().toString()
				when (response.isSuccessful) {
					true ->
						Either.Right(transform((response.body() ?: default)))
					false -> {
						Either.Left(Failure.ServerError(Failure.ErrorReport.empty()))
					}
				}
			} catch (e1: JsonSyntaxException) {
				Either.Left(Failure.JsonFormatException(Failure.ErrorReport.empty()))
				
			} catch (exception: Throwable) {
				exception.printStackTrace()
				Either.Left(Failure.ServerError(Failure.ErrorReport.empty()))
			}
		}
	}
}

