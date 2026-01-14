package com.felixjhonata.inventorymanager.repository

import com.felixjhonata.inventorymanager.model.entity.Activity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
  fun getActivities(): Flow<List<Activity>>

  suspend fun insertActivity(activity: Activity)

  suspend fun updateActivity(activity: Activity)

  suspend fun deleteActivity(activity: Activity)
}