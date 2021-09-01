package com.test.kotlinapplication

import android.app.Application
import com.test.kotlinapplication.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CurrencyInfoApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

//    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
//    val repository by lazy { CurrencyInfoRepository(database.getCurrencyInfoEntityDao()) }
}