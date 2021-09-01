package com.test.kotlinapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.kotlinapplication.R
import com.test.kotlinapplication.model.CurrencyInfo
import java.util.ArrayList

class CurrencyListRecyclerAdapter(private val data: ArrayList<CurrencyInfo>) :
        RecyclerView.Adapter<CurrencyListRecyclerAdapter.CurrencyInfoHolder>() {

    inner class CurrencyInfoHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.textView)
        val name: TextView = view.findViewById(R.id.textView2)
        val symbol: TextView = view.findViewById(R.id.textView3)

        init {
            view.setOnClickListener {

            }
        }

        fun bind(currencyInfo: CurrencyInfo) {
            id.text = currencyInfo.id
            name.text = currencyInfo.name
            symbol.text = currencyInfo.symbol
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyInfoHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return CurrencyInfoHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyInfoHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}