package com.felixjhonata.inventorymanager.repository

import androidx.paging.PagingSource
import com.felixjhonata.inventorymanager.model.entity.Activity
import com.felixjhonata.inventorymanager.model.entity.ActivityWithProduct

interface ActivityRepository {
  fun getActivitiesWithProduct(): PagingSource<Int, ActivityWithProduct>

  suspend fun insertActivity(activity: Activity)

  suspend fun updateActivity(activity: Activity)

  suspend fun deleteActivity(activity: Activity)
}