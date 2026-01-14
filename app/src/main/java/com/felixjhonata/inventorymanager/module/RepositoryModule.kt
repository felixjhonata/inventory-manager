package com.felixjhonata.inventorymanager.module

import com.felixjhonata.inventorymanager.repository.ActivityRepository
import com.felixjhonata.inventorymanager.repository.ProductRepository
import com.felixjhonata.inventorymanager.repository.impl.ActivityRepositoryImpl
import com.felixjhonata.inventorymanager.repository.impl.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  abstract fun bindsProductRepository(
    productRepositoryImpl: ProductRepositoryImpl
  ): ProductRepository

  @Binds
  abstract fun bindsActivityRepository(
    activityRepository: ActivityRepositoryImpl
  ): ActivityRepository

}