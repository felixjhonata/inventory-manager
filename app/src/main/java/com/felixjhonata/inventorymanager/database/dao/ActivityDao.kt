package com.felixjhonata.inventorymanager.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.felixjhonata.inventorymanager.model.entity.Activity
import com.felixjhonata.inventorymanager.model.entity.ActivityWithProduct

@Dao
interface ActivityDao {
  @Query("SELECT * FROM Activity as a JOIN Product as p ON a.product_sku = p.product_sku")
  fun getActivitiesWithProduct(): PagingSource<Int, ActivityWithProduct>

  @Insert
  suspend fun insertActivity(activity: Activity)

  @Update
  suspend fun updateActivity(activity: Activity)

  @Delete
  suspend fun deleteActivity(activity: Activity)
}