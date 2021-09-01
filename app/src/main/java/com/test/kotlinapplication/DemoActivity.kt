package com.test.kotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.kotlinapplication.db.AppDatabase
import com.test.kotlinapplication.db.CurrencyInfoEntity
import com.test.kotlinapplication.model.CurrencyInfo
import com.test.kotlinapplication.ui.main.CurrencyListFragment
import kotlinx.coroutines.*
import kotlin.collections.ArrayList

class DemoActivity : AppCompatActivity() {

    var ascending = true
    companion object {
        val currencyInfoList: ArrayList<CurrencyInfo> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        fetchDataFromAssets();

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CurrencyListFragment.newInstance())
                    .commitNow()
        }

        val loadButton: Button = findViewById(R.id.button1)
        loadButton.setOnClickListener {
            loadData()
        }

        val sortButton: Button = findViewById(R.id.button2)
        sortButton.setOnClickListener {
            sortData(ascending)
            ascending = !ascending
        }
    }

    private fun fetchDataFromAssets() {
        val jsonFileString = Utils.getJsonData(applicationContext, "currencylist.json")
        val gson = Gson()
        val listCurrencyInfoType = object : TypeToken<List<CurrencyInfo>>() {}.type
        val currencyList: List<CurrencyInfo> = gson.fromJson(jsonFileString, listCurrencyInfoType)
        val currencyEntities: MutableList<CurrencyInfoEntity> = mutableListOf()
        currencyList.forEach { item -> currencyEntities.add(CurrencyInfoEntity(item.id, item.name, item.symbol)) }

        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getDatabase(applicationContext).getCurrencyInfoEntityDao().insertAllCurrencies(*currencyEntities.toTypedArray())
        }
    }

    private fun loadData() {
        currencyInfoList.clear()
        CoroutineScope(Dispatchers.IO).launch {
            val currencyInfoEntities: List<CurrencyInfoEntity> = AppDatabase.getDatabase(applicationContext).getCurrencyInfoEntityDao().getAll()
            currencyInfoEntities.forEach {
                item -> currencyInfoList.add(CurrencyInfo(item.id, item.name, item.symbol))
            }

            val bundle = Bundle()

            bundle.putParcelableArrayList("a", currencyInfoList);
            val fragment = CurrencyListFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.main, fragment).commit()
        }
    }

    private fun sortData(isAsc: Boolean) {
        if (isAsc) {
            currencyInfoList.sortBy { it.id }
        } else {
            currencyInfoList.sortByDescending { it.id }
        }

        val bundle = Bundle()
        bundle.putParcelableArrayList("a", currencyInfoList);
        val fragment = CurrencyListFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main, fragment).commit()
    }
}