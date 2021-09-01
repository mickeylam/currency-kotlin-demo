package com.test.kotlinapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
class CurrencyInfoEntity(
        @PrimaryKey var id: String,
        var name: String,
        var symbol: String,
)
