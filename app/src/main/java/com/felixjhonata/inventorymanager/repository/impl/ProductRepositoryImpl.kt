package com.felixjhonata.inventorymanager.repository.impl

import androidx.paging.PagingSource
import com.felixjhonata.inventorymanager.database.dao.ProductDao
import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
  private val productDao: ProductDao
): ProductRepository {
  override fun getProducts(): PagingSource<Int, Product> {
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