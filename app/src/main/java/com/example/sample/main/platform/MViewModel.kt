package com.example.sample.main.platform

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sample.base.exception.Failure
import com.example.sample.base.interactor.UseCase
import com.example.sample.core.domain.BitCoinResponse
import com.example.sample.core.interactor.GetBitCoinResponse
import com.example.sample.main.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MViewModel @Inject constructor(private val getBitCoinResponse: GetBitCoinResponse) :
	ViewModel() {
	private var mBitCoinResponse: MutableLiveData<BitCoinResponse> = MutableLiveData()
	internal val mValue: MutableLiveData<String> = MutableLiveData()
	var mfailure: MutableLiveData<Failure> = MutableLiveData()
	
	@AppModule.SharedPreferenceApp
	@Inject
	lateinit var prefs: SharedPreferences
	fun getLanguageValue() = prefs.getString("language", "USD")
	
	fun setDisplayValue(language: String?) {
		
		val bpi = mBitCoinResponse.value?.bpi
		mValue.value =
			when (language) {
				"USD" -> bpi?.USD?.code + " " + bpi?.USD?.rate
				"GBP" -> bpi?.GBP?.code + " " + bpi?.GBP?.rate
				else -> bpi?.EUR?.code + " " + bpi?.EUR?.rate
			}
	}
	
	fun fetchBitcoinData() =
		getBitCoinResponse(UseCase.None()) {
			fun handleBitCoinResponse(bitCoinResponse: BitCoinResponse) {
				this.mBitCoinResponse.value = bitCoinResponse
				setDisplayValue(getLanguageValue())
			}
			it.fold(::handleFailure, ::handleBitCoinResponse)
		}
	
	fun handleFailure(failure: Failure) {
		this.mfailure.value = failure
	}
}