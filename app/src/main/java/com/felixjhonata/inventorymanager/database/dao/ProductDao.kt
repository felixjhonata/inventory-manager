package com.felixjhonata.inventorymanager.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.felixjhonata.inventorymanager.model.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
  @Query("SELECT * FROM Product")
  fun getProducts(): Flow<List<Product>>

  @Insert
  suspend fun insertProduct(product: Product)

  @Update
  suspend fun updateProduct(product: Product)

  @Delete
  suspend fun deleteProduct(product: Product)
}