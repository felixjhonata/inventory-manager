package com.felixjhonata.inventorymanager.repository.impl

import com.felixjhonata.inventorymanager.database.dao.ActivityDao
import com.felixjhonata.inventorymanager.model.entity.Activity
import com.felixjhonata.inventorymanager.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
  private val activityDao: ActivityDao
): ActivityRepository {
  override fun getActivities(): Flow<List<Activity>> {
    return activityDao.getActivities()
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