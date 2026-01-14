package com.felixjhonata.inventorymanager.repository.impl

import com.felixjhonata.inventorymanager.database.dao.ProductDao
import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
  private val productDao: ProductDao
): ProductRepository {
  override fun getProducts(): Flow<List<Product>> {
    return productDao.getProducts()
  }

  override suspend fun insertProduct(product: Product) {
    return productDao.insertProduct(product)
  }

  override suspend fun updateProduct(product: Product) {
    return productDao.updateProduct(product)
  }

  override suspend fun deleteProduct(product: Product) {
    return productDao.deleteProduct(product)
  }
}