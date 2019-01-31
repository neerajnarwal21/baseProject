package com.example.sample.utils

object TokenRefresh {
    private var tokenListener: (() -> Unit)? = null

    fun setTokenListener(tokenListener: (() -> Unit)?) {
        this.tokenListener = tokenListener
    }

    fun tokenRefreshComplete() {
        tokenListener?.invoke()
    }
}