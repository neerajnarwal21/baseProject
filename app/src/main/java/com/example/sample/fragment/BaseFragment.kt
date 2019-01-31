package com.example.sample.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.TextView
import com.example.sample.activity.BaseActivity
import com.example.sample.activity.MainActivity
import com.example.sample.di.BaseApp
import com.example.sample.di.module.ActivityModule
import com.example.sample.retrofitManager.ApiClient
import com.example.sample.retrofitManager.ApiInterface
import com.example.sample.retrofitManager.ApiManager
import com.example.sample.retrofitManager.ResponseListener
import com.example.sample.utils.PrefStore
import retrofit2.Call
import javax.inject.Inject


/**
 * Created by Neeraj Narwal on 2/5/17.
 */
open class BaseFragment : Fragment(), AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener, ResponseListener {

    lateinit var baseActivity: BaseActivity
    lateinit var apiInterface: ApiInterface
    @Inject
    lateinit var apiClient: ApiClient
    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var store: PrefStore

    override fun onAttach(context: Context) {
        (context.applicationContext as BaseApp).getInjection().activityModule(ActivityModule(activity!!)).build().inj(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity
        baseActivity.hideSoftKeyboard()
        apiInterface = apiClient.client.create(ApiInterface::class.java)
    }

    fun setToolbar(showDrawer: Boolean, title: String, showUserAdd: Boolean, showClose: Boolean) {
        (baseActivity as MainActivity).setToolbar(showDrawer, title, showUserAdd, showClose);
    }

    override fun onResume() {
        super.onResume()
        activity!!.invalidateOptionsMenu()
    }

    override fun onClick(v: View) {

    }

    fun showToast(msg: String) {
        baseActivity.showToast(msg, false)
    }

    fun showToast(msg: String, isError: Boolean) {
        baseActivity.showToast(msg, isError)
    }

    public fun log(s: String) {
        baseActivity.log(s)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {

    }

    fun getText(textView: TextView): String {
        return textView.text.toString().trim { it <= ' ' }
    }

    override fun onSuccess(call: Call<*>, message: String, payload: Any) {

    }

    override fun onError(call: Call<*>, statusCode: Int, errorMessage: String, responseListener: ResponseListener) {
        baseActivity.onError(call, statusCode, errorMessage, responseListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("API>>>>> cancelling calls")
        apiManager.cancelAnyCall()
    }
}
