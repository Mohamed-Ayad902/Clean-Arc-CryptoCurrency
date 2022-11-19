package com.example.cleancryptocurrency.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoinEntity::class], version = 1)
abstract class CoinsDatabase :RoomDatabase(){
    abstract val dao:CoinDao
}