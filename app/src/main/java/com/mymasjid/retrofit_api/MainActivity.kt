package com.mymasjid.retrofit_api

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rf = Retrofit.Builder()
            .baseUrl(RetrofitModel.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = rf.create(RetrofitModel::class.java)
        val call = api.getPosts()

        call?.enqueue(object:Callback<List<PostModel>>{
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                if(response.isSuccessful){
                    val postModel = response.body()
                    postModel?.let {
                        val post = Array<String>(it.size){""}

                        for(i in it.indices)
                            post[i] = it[i].title.toString()
                        val listview : ListView = findViewById(R.id.listView)
                        val adepter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,post)
                        listview.adapter = adepter
                    }
                }
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}
