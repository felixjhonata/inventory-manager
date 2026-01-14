package com.felixjhonata.inventorymanager.repository

import com.felixjhonata.inventorymanager.model.entity.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
  fun getProducts(): Flow<List<Product>>

  suspend fun insertProduct(product: Product)

  suspend fun updateProduct(product: Product)

  suspend fun deleteProduct(product: Product)
}