package com.example.sample.retrofitManager

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by Neeraj Narwal on 5/5/17.
 */
interface ApiInterface {

    @Multipart
    @POST("login")
    fun login(@Part("email") email: RequestBody,
              @Part("password") password: RequestBody,
              @Part("firebase_token") deviceToken: RequestBody,
              @Part("role") role: RequestBody)
            : Call<JsonObject>

    @Multipart
    @POST("reset-password")
    fun forgotPassword(@Part("email") email: RequestBody)
            : Call<JsonObject>

    @Multipart
    @POST("customer-mobile-register")
    fun submitPhoneNumber(@Part("mobile_number") mobile: RequestBody)
            : Call<JsonObject>

    @Multipart
    @POST("customer-mobile-verification")
    fun confirmOTP(@Part("mobile_number") mobile: RequestBody,
                   @Part("otp") otp: RequestBody)
            : Call<JsonObject>

    @Multipart
    @POST("customer-registration-loan")
    fun formStep4(@Part("customer_loans_id") loanId: RequestBody?,
                  @Part("params") loandata: RequestBody,
                  @Part sign: MultipartBody.Part?): Call<JsonObject>


    @GET("all-loans")
    fun allLoans(): Call<JsonObject>

    @GET
    fun searchIfsc(@Url url: String): Call<JsonObject>
}
