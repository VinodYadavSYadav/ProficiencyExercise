package com.proficiency.utils

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface  Api {
    @GET("facts.json")
    fun getString(): Call<String>
}