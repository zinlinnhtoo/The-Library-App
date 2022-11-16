//package com.example.thelibraryapp.network.dataagents
//
//import com.example.thelibraryapp.data.vos.OverviewListVO
//import com.example.thelibraryapp.network.NewYorkTimesApi
//import com.example.thelibraryapp.network.responses.BookListResponse
//import com.example.thelibraryapp.utils.BASE_URL
//import okhttp3.OkHttpClient
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object BookDataAgentImpl: BookDataAgent {
//
//    private var mNewYorkTimesApi: NewYorkTimesApi? = null
//
//    init {
//        val mOkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(mOkHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        mNewYorkTimesApi = retrofit.create(NewYorkTimesApi::class.java)
//    }
//
//    override fun getOverview(onSuccess: (List<OverviewListVO>) -> Unit, onFailure: (String) -> Unit) {
//        mNewYorkTimesApi?.getOverview()?.enqueue(
//            object : Callback<BookListResponse> {
//                override fun onResponse(
//                    call: Call<BookListResponse>,
//                    response: Response<BookListResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val bookList = response.body()?.results?.lists ?: listOf()
////                        Log.println(Log.INFO, "Book List", bookList.toString())
//                        onSuccess(bookList)
//                    } else {
//                        onFailure(response.message())
//                    }
//                }
//
//                override fun onFailure(call: Call<BookListResponse>, t: Throwable) {
//                    onFailure(t.message ?: "")
//                }
//
//            }
//        )
//    }
//}