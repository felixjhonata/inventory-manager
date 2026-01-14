package com.felixjhonata.inventorymanager.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
  @PrimaryKey @ColumnInfo(name = "product_sku") val sku: String,
  @ColumnInfo(name = "product_name") val productName: String,
  @ColumnInfo(name = "amount") val amount: Int
)