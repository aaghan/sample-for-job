package com.example.sample.core.domain

import com.example.sample.base.extension.empty

data class GBP(
    val code: String,
    val description: String,
    val rate: String,
    val rate_float: Double,
    val symbol: String
){
    companion object{
        fun empty() = GBP(String.empty(),String.empty(),String.empty(),0.0,String.empty(),)
    }
}