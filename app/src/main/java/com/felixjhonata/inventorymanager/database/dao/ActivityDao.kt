package com.felixjhonata.inventorymanager.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.felixjhonata.inventorymanager.model.entity.Activity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
  @Query("SELECT * FROM Activity")
  fun getActivities(): Flow<List<Activity>>

  @Insert
  suspend fun insertActivity(activity: Activity)

  @Update
  suspend fun updateActivity(activity: Activity)

  @Delete
  suspend fun deleteActivity(activity: Activity)
}