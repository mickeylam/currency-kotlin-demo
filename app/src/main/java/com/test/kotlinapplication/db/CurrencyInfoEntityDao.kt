package com.test.kotlinapplication.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyInfoEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currencyInfoEntity: CurrencyInfoEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrencies(vararg currencyInfoEntity: CurrencyInfoEntity?)

    @Delete
    suspend fun deleteCurrency(currencyInfoEntity: CurrencyInfoEntity?)

    @Query("SELECT * FROM currencyinfoentity")
    suspend fun getAll(): List<CurrencyInfoEntity>

    @Query("SELECT * FROM currencyinfoentity ORDER BY CASE WHEN :isAsc = 1 THEN id END ASC, CASE WHEN :isAsc = 0 THEN id END DESC")
    fun getCurrencyInfoListByOrder(isAsc: Boolean): List<CurrencyInfoEntity?>?
}