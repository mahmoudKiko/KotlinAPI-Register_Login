package com.example.kotlinapi.api

import com.example.kotlinapi.model.DefaultRespoonse
import com.example.kotlinapi.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api
{
@FormUrlEncoded
@POST("registerUser.php")
fun createUser(
    @Field("username") username:String ,
    @Field("phone") phone:String ,
    @Field("email") email:String ,
    @Field("password") password:String ,
    @Field("address") address:String
):Call<DefaultRespoonse>


    @FormUrlEncoded
    @POST("userLogin.php")
    fun userLogin(
        @Field("phone") email:String,
        @Field("password") password: String
    ):Call<LoginResponse>
}