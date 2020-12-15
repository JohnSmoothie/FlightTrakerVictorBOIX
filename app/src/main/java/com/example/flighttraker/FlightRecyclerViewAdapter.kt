package com.example.flighttraker

import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FlightRecyclerViewAdapter : RecyclerView.Adapter<FlightRecyclerViewAdapter.MyViewHolder>() {

    var listVols : List<FlightModel>? = null
    var onItemClickListener : AdapterView.OnItemClickListener? = null

    interface OnItemClickListener{
        fun onItemClicked(flightName : String)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(InfoVolsView(parent.context))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val myView = holder.itemView as InfoVolsView
        myView.afficherInfos(listVols!![position])
        myView.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return listVols!!.size
    }
}