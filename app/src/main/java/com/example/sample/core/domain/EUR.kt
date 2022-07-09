package com.example.sample.core.domain

import com.example.sample.base.extension.empty

data class EUR(
    val code: String,
    val description: String,
    val rate: String,
    val rate_float: Double,
    val symbol: String
){
    companion object{
        fun empty() = EUR(String.empty(),String.empty(),String.empty(),0.0,String.empty(),)
    }
}