package com.example.postapiexample.model.request

data class CustomerList(
    val id: String,
    val jsonrpc: String,
    val method: String,
    val params: Params
)

data class Params(
    val custId: Int,
    val fullSync: Boolean
)