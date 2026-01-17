package com.felixjhonata.inventorymanager.repository.impl

import androidx.paging.PagingSource
import com.felixjhonata.inventorymanager.database.dao.ActivityDao
import com.felixjhonata.inventorymanager.model.entity.Activity
import com.felixjhonata.inventorymanager.model.entity.ActivityWithProduct
import com.felixjhonata.inventorymanager.repository.ActivityRepository
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
  private val activityDao: ActivityDao
): ActivityRepository {
  override fun getActivitiesWithProduct(): PagingSource<Int, ActivityWithProduct> {
    return activityDao.getActivitiesWithProduct()
  }

  override suspend fun insertActivity(activity: Activity) {
    return activityDao.insertActivity(activity)
  }

  override suspend fun updateActivity(activity: Activity) {
    return activityDao.updateActivity(activity)
  }

  override suspend fun deleteActivity(activity: Activity) {
    return activityDao.deleteActivity(activity)
  }
}