package com.test.kotlinapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyInfo(val id: String, val name: String, val symbol: String) : Parcelable