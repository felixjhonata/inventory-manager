package com.felixjhonata.inventorymanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felixjhonata.inventorymanager.database.dao.ActivityDao
import com.felixjhonata.inventorymanager.database.dao.ProductDao
import com.felixjhonata.inventorymanager.model.entity.Activity
import com.felixjhonata.inventorymanager.model.entity.Product

@Database(entities = [Product::class, Activity::class], version = 1, exportSchema = false)
abstract class InventoryManagerDatabase: RoomDatabase() {
  abstract fun productDao(): ProductDao
  abstract fun activityDao(): ActivityDao
}