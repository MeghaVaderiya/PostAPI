package com.example.postapiexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postapiexample.R
import com.example.postapiexample.model.response.CustomerListResponse
import com.example.postapiexample.model.response.Result

class MainActivityAdapter(var context: Context, var mlist: List<Result>):
    RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        lateinit var txt1: TextView
        lateinit var txt2: TextView
        lateinit var txt3: TextView
        lateinit var txt4: TextView


        init {

            txt1=itemView.findViewById(R.id.textView1)
            txt2=itemView.findViewById(R.id.textView2)
            txt3=itemView.findViewById(R.id.textView3)
            txt4=itemView.findViewById(R.id.textView4)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        lateinit var  aContext: Context
        val v= LayoutInflater.from(parent.context).inflate(R.layout.designfile,parent,false)
        aContext=parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txt1.text= mlist[position].id.toString()
        holder.txt3.text= mlist[position].items.toString()
        holder.txt2.text=mlist[position].custid.toString()
        holder.txt4.text=mlist[position].order_number.toString()

    }

    override fun getItemCount(): Int {
        return mlist.size
    }
}