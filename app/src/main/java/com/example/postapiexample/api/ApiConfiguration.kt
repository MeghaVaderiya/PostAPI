package com.example.uidesign.Model

import com.example.postapiexample.model.request.CustomerList
import com.example.postapiexample.model.response.CustomerListResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*
import kotlin.collections.HashMap

interface ApiConfiguration{


@POST("public/index.php/service/edar")
fun getResult(
    @Body modelDataClass: CustomerList): Call<CustomerListResponse>
}