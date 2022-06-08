package com.example.maymay

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MemeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.memes_recycler_view)

        adapter = MemeAdapter(this)
        getMemes()
    }

    private fun getMemes() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgflip.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: MemeAPI = retrofit.create(MemeAPI::class.java)

        val call: Call<MemeModel> = retrofitAPI.getMemes()

        // on below line we are executing our method.
        call.enqueue(object : Callback<MemeModel> {

            override fun onResponse(call: Call<MemeModel>, response: Response<MemeModel>) {

                response.body()?.let {
                    adapter.setData(it)

                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                }
            }

            override fun onFailure(call: Call<MemeModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}
