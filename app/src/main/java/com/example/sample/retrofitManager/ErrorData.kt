package com.example.sample.retrofitManager

import com.google.gson.annotations.SerializedName

/**
 * Created by neeraj on 15/6/17.
 */
data class ErrorData(@SerializedName("message") var message: String? = null)
