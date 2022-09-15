package com.example.postapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postapiexample.adapter.MainActivityAdapter
import com.example.postapiexample.api.RetrofitHelper
import com.example.postapiexample.model.request.CustomerList
import com.example.postapiexample.model.request.Params
import com.example.postapiexample.model.response.CustomerListResponse
import com.example.postapiexample.repository.UserRepository
import com.example.quotesapp.viewModel.MainViewModel
import com.example.quotesapp.viewModel.MainViewModelFactory
import com.example.uidesign.Model.ApiConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var mainActivityAdapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitHelper=RetrofitHelper.getInstance().create(ApiConfiguration::class.java)
        val repository=UserRepository(retrofitHelper)
        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.quotes.observe(this,{
            Log.d("Data",it.result.toString())
            Toast.makeText(this,"Result" + it.result.toString(),Toast.LENGTH_SHORT).show()


        })


        getData()




    }
    fun getData() {

        val request = CustomerList(
            id = "82F85DB43CBF6",
            jsonrpc = "2.0",
            method = "getDarEquipments",
            params = Params(custId = 60,
                fullSync = true)
        )

        var privacy: Call<CustomerListResponse> = RetrofitHelper.getServiceAPI()!!.getResult(request)

        privacy.enqueue(object : Callback<CustomerListResponse> {
            override fun onResponse(
                call: Call<CustomerListResponse>,
                response: Response<CustomerListResponse>
            ) {
                try {

                    Log.e("response",response.body().toString())

                    datarec.layoutManager= LinearLayoutManager(this@MainActivity)
                        datarec.adapter=MainActivityAdapter(this@MainActivity, response.body()!!.result)
                     Toast.makeText(this@MainActivity,"Data Get" + response.body().toString(),Toast.LENGTH_SHORT).show()


                } catch (e: Exception) {
                    Log.e("saurav", e.toString())
                    Toast.makeText(this@MainActivity, e.toString(), Toast.LENGTH_SHORT).show()


                }

            }


            override fun onFailure(call: Call<CustomerListResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

/*
    private fun initializeRecyclerView() {

        mainActivityAdapter = MainActivityAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mainActivityAdapter
        }
    }
*/
}

private fun <T> LiveData<T>.observe(callback: Callback<T>) {

}
