package com.example.sample.retrofitManager

import android.content.Context
import android.util.MalformedJsonException
import com.example.sample.utils.Const
import com.example.sample.utils.Const.ErrorCodes.*
import com.example.sample.utils.debugLog
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

/**
 * Created by Neeraj Narwal on 31/5/17.
 */
class ApiManager(val context: Context, val progressDialog: ProgressDialog) {

//    fun initProgressDialog(activity: Activity) {
//        progressDialog = ProgressDialog.getInstance()
//        progressDialog.initiateProgressDialog(activity)
//    }

    internal val TAG = ApiManager::class.java.simpleName
    private val apiResponseHashMap = HashMap<Call<*>, ResponseListener>()


    //Constructor when Progress message need to update
    fun <T> makeApiCall(call: Call<T>, responseListener: ResponseListener, progressMessage: String?) {
        if (progressMessage != null && !progressMessage.isEmpty()) {
            progressDialog.updateMessage(progressMessage)
        }
        makeApiCall(call, responseListener)
    }

    @JvmOverloads
    fun <T> makeApiCall(call: Call<T>, responseListener: ResponseListener, showProgress: Boolean = true) {
        try {
            apiResponseHashMap[call] = responseListener

//            call.enqueue(this)
            if (showProgress) {
                progressDialog.startProgressDialog()
            }

            //Logs post URL
            log(call.request().url().toString())

            //Logs post params of Multipart request
            log("Post Params >>>> \n" + bodyToString(call.request().body())
                    .replace("\r", "")
                    .replace("--+[a-zA-Z0-9-/:=; ]+\\n".toRegex(), "")
                    .replace("Content+[a-zA-Z0-9-/:=; ]+;\\s".toRegex(), "")
                    .replace("Content+[a-zA-Z0-9-/:=; ]+\\n".toRegex(), "")
                    .replace("charset+[a-zA-Z0-9-/:=; ]+\\n".toRegex(), "")
                    .replace("name=", "")
                    .replace("\n\n", "--> ")
            )
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    handleResponse(call, response)
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    handleFailure(call, t)
                }

            })

//            call.enqueue(callback2({ cal, response ->
//
//            }, { cal, t ->
//
//            }))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

//    fun <T> callback2(success: ((call: Call<T>, Response<T>) -> Unit)?, failure: ((call: Call<T>, t: Throwable) -> Unit)? = null): Callback<T> {
//        return object : Callback<T> {
//            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
//                success?.invoke(call, response)
//            }
//
//            override fun onFailure(call: Call<T>, t: Throwable) {
//                failure?.invoke(call, t)
//            }
//        }
//    }

    private fun log(string: String) {
        debugLog(TAG, string)
    }

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }

    fun <T> handleResponse(call: Call<T>, response: Response<T>) {
        progressDialog.stopProgressDialog()
        val responseListener = apiResponseHashMap[call]
        try {
            val body = response.body() as JsonObject
            log(body.toString())
            val code = body.get("status").asInt
            if (code == 200) {
                responseListener?.onSuccess(call, body.asJsonObject.get("message").asString, body.asJsonObject.get("payload").asJsonObject)
            } else {
                val parser = JsonParser()
                val mJson = parser.parse(body.toString())
                val errorResponse = Gson().fromJson(mJson, ErrorData::class.java)
                responseListener?.onError(call, code, errorResponse.message!!, responseListener)
            }
        } catch (e: Exception) {
            log(call.request().url().toString() + "\n" + e.localizedMessage + "\n\n" + e.printStackTrace())
        }
        apiResponseHashMap.remove(call)
    }

    fun <T> handleFailure(call: Call<T>, t: Throwable) {
        progressDialog.stopProgressDialog()
        val responseListener = apiResponseHashMap[call]
        try {
            when (t.javaClass) {
                SocketTimeoutException::class.java ->
                    responseListener?.onError(call, SOCKET_TIMEOUT, "Request Timeout", responseListener)
                MalformedJsonException::class.java ->
                    responseListener?.onError(call, SERVER_ERROR, "Internal server error", responseListener)
                UnknownHostException::class.java ->
                    responseListener?.onError(call, NO_INTERNET, "No Network", responseListener)
                else ->
                    responseListener?.onError(call, SERVER_ERROR, t.localizedMessage, responseListener)
            }
        } catch (ignored: Exception) {
        }
        //        responseListener.onFinish();
        apiResponseHashMap.remove(call)
    }

    fun cancelAnyCall() {
        try {
            log("API>>>>> Size : " + apiResponseHashMap.size)
            for (call in apiResponseHashMap.keys) {
                call.cancel()
                apiResponseHashMap.remove(call)
            }

        } catch (ignored: Exception) {
        }
    }

}