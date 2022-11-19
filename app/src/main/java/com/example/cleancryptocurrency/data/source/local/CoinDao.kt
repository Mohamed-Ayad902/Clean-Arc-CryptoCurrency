package com.example.cleancryptocurrency.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<CoinEntity>)

    @Query("select * from coinentity")
    fun getCoins(): List<CoinEntity>

    @Query("DELETE FROM coinentity WHERE id IN(:coins)")
    suspend fun deleteCoins(coins: List<CoinEntity>)

}