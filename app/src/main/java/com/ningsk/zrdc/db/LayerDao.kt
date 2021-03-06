package com.ningsk.zrdc.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ningsk.zrdc.entity.dic.Layer

/**
 * =======================================
 *
 * Create by ningsikai 2020/5/25:10:34 AM
 * ========================================
 */

@Dao
interface LayerDao {
    @Query("SELECT * FROM LAYER WHERE isBaseMap  = 1 ORDER BY layerIndex  ASC")
    fun getShpDatasourceList(): LiveData<List<Layer>>

    @Query("SELECT * FROM LAYER WHERE isBaseMap = 0 ORDER BY layerIndex ASC")
    fun getTpkDatasourceList(): LiveData<List<Layer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(layer: Layer)

    @Update
    suspend fun update(layer: Layer)

    @Delete
    suspend fun delete(layer: Layer)
}