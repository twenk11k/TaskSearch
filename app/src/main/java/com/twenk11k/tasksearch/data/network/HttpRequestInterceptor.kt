package com.twenk11k.tasksearch.data.network

import com.twenk11k.tasksearch.util.Constants.BEARER
import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", "Bearer $BEARER").build()
        return chain.proceed(request)
    }

}