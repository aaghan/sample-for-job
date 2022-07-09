package com.example.sample.apiService;

import com.example.sample.core.domain.BitCoinResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiInterface {
	public static final String BIT_COIN_RESPONSE = "v1/bpi/currentprice.json";
	@GET(BIT_COIN_RESPONSE)
	public Call<BitCoinResponse> getBitCoinResponse() ;
}
