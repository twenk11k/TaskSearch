package com.twenk11k.tasksearch.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", "Bearer 2N6edq_uS3ACq89RhzN2yQtdT5aEhbKgaE5-P9BD3hc").build()
        return chain.proceed(request)
    }

}