package com.example.postapiexample.api

import com.example.uidesign.Model.ApiConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitHelper {

    private const val BASE_URL = "https://tpwebservicesdev.ticketproweb.com"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServiceAPI(): ApiConfiguration? {
        return getApiClient(BASE_URL)!!.create(ApiConfiguration::class.java)


    }

    fun getApiClient(BASE_URL: String?): Retrofit? {
        /* val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        if (retrofit == null) {
            val gson = GsonBuilder().setLenient().create()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }*/

        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()

        val okHttpClient =
            builder.addInterceptor(ResponseInterceptor()).addInterceptor(httpLoggingInterceptor)
                .addInterceptor(Interceptor { chain ->
                    val request: Request =
                        chain.request().newBuilder().addHeader("timeZone", "Asia/Kolkata")
                            .addHeader("noAuthorisation", "yes")
                            /* .addHeader(
                                 "authorisation",
                                 "aad7e88c9b5040d3dca87120f076d5ac448e60c9e111fde2a33e2b1382f91b1a026304fd96934c419b1bd220cc9189c88888408d16362c1102919585694b0c55"
                             )*/
                            .build()
                    chain.proceed(request)
                })

        val retrofit = Retrofit.Builder().client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL).build()

        return retrofit
    }

    class ResponseInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            return response.newBuilder()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build()
        }
    }


}