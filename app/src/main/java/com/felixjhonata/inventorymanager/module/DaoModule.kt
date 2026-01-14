package com.felixjhonata.inventorymanager.module

import android.content.Context
import androidx.room.Room
import com.felixjhonata.inventorymanager.R
import com.felixjhonata.inventorymanager.database.InventoryManagerDatabase
import com.felixjhonata.inventorymanager.database.dao.ActivityDao
import com.felixjhonata.inventorymanager.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

  @Provides
  @Singleton
  fun provideDatabase(
    @ApplicationContext context: Context
  ): InventoryManagerDatabase {
    return Room.databaseBuilder(
      context,
      InventoryManagerDatabase::class.java,
      name = context.getString(R.string.app_database)
    ).fallbackToDestructiveMigration(true).build()
  }

  @Provides
  fun provideProductDao(
    db: InventoryManagerDatabase
  ): ProductDao {
    return db.productDao()
  }

  @Provides
  fun providesActivityDao(
    db: InventoryManagerDatabase
  ): ActivityDao {
    return db.activityDao()
  }

}