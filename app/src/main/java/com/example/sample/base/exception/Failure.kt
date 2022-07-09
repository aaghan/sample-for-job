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
package com.example.sample.base.exception

import com.example.sample.base.exception.Failure.FeatureFailure
import com.example.sample.base.extension.empty
import java.io.Serializable


/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
public sealed class Failure : Serializable {
	
	data class ErrorReport(val stackTrace: String, val respnsecode: String) : Serializable {
		companion object {
			fun empty() = ErrorReport(String.empty(), String.empty())
		}
	}
	
	object NetworkConnection : Failure()
	object LoginFailure : Failure()
	class ServerError(val errorReports: ErrorReport) : FeatureFailure(errorReports)
	object ServerError2 : Failure()
	class JsonFormatException(val errorReports: ErrorReport) : FeatureFailure(errorReports)
	
	/** * Extend this class for feature specific failures.*/
	abstract class FeatureFailure(val errorReport: ErrorReport) : Failure()
	
	
	companion object {
		fun getMessageFromFailure(paramFailure: Failure): String =
			when (paramFailure) {
				
				is NetworkConnection -> {
					"Network is not reachable, PLease check your Network, Date and Time. If the issue persist, PLease contact your service provider"
				}
				is ServerError -> {
					"Server Error Occurred. Please try again after some Time. If the issue persist, PLease contact your service provider"
				}
				is JsonFormatException -> {
					"Could not fetch the Error Reason. Please try again after some Time. If the issue persist, PLease contact your service provider"
				}
				is ServerError2 -> {
					"Too Many Request"
				}
				else -> {
					"Unknown Error Occurred. Please try again after some Time. If the issue persist, PLease contact your service provider"
				}
				
			}
		
	}
	
}
