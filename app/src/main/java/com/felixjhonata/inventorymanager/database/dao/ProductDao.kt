package com.felixjhonata.inventorymanager.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.felixjhonata.inventorymanager.model.entity.Product

@Dao
interface ProductDao {
  @Query("SELECT * FROM Product")
  fun getProducts(): PagingSource<Int, Product>

  @Insert
  suspend fun insertProduct(product: Product)

  @Update
  suspend fun updateProduct(product: Product)

  @Delete
  suspend fun deleteProduct(product: Product)
}