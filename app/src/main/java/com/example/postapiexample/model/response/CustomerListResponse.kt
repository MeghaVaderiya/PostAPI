package com.example.postapiexample.model.response

data class CustomerListResponse(
    val id: String,
    val jsonrpc: String,
    val result: List<Result>
) {
}

data class Result(
    val custid: Int,
    val id: Int,
    val items: String,
    val order_number: Int
)