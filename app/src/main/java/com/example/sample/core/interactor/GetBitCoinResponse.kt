package com.example.sample.core.interactor

import com.example.sample.base.exception.Failure
import com.example.sample.base.functional.Either
import com.example.sample.base.interactor.UseCase
import com.example.sample.core.data.AppCoreRepo
import com.example.sample.core.domain.BitCoinResponse

import javax.inject.Inject

public class GetBitCoinResponse
@Inject constructor(private val appCoreRepo: AppCoreRepo): UseCase<BitCoinResponse, UseCase.None>(){
    override suspend fun run(params: UseCase.None/*Params*/): Either<Failure, BitCoinResponse> = appCoreRepo.getBitCoinResponse()

}