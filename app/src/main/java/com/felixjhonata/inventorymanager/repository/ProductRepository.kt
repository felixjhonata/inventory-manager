package com.felixjhonata.inventorymanager.repository

import androidx.paging.PagingSource
import com.felixjhonata.inventorymanager.model.entity.Product

interface ProductRepository {
  fun getProducts(): PagingSource<Int, Product>

  suspend fun insertProduct(product: Product)

  suspend fun updateProduct(product: Product)

  suspend fun deleteProduct(product: Product)
}