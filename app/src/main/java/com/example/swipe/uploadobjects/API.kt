//Establishing secure connection for object uploading.

package com.example.swipe.uploadobjects

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private const val _url = "https://app.getswipe.in/api/public/"

private val retrofit = Retrofit.Builder().baseUrl(_url)
    .addConverterFactory(GsonConverterFactory.create()).build()

val api = retrofit.create(APIs::class.java)

interface APIs {

    @FormUrlEncoded
    @POST("add")
    suspend fun addProduct(@FieldMap metadata : Map<String,String>):Response<PostData>

}