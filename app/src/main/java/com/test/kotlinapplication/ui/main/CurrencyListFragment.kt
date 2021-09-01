package com.test.kotlinapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.kotlinapplication.R
import com.test.kotlinapplication.adapter.CurrencyListRecyclerAdapter
import com.test.kotlinapplication.model.CurrencyInfo
import java.util.*

class CurrencyListFragment : Fragment() {
    companion object {
        fun newInstance(): CurrencyListFragment {
            return CurrencyListFragment()
        }
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler)
        var currencyInfos: ArrayList<CurrencyInfo> = ArrayList()
        if (arguments != null) {
            currencyInfos = arguments?.getParcelableArrayList("a")!!
        }
        val adapter = CurrencyListRecyclerAdapter(currencyInfos)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        return view
    }
}