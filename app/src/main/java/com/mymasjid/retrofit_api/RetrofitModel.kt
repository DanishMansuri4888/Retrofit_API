package com.mymasjid.retrofit_api

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitModel {

        @GET("Posts")
        fun getPosts() : Call<List<PostModel>>

    companion object {
        const val Base_Url = "https://jsonplaceholder.typicode.com/"
    }
}
