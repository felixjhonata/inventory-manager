package com.felixjhonata.inventorymanager.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  foreignKeys = [
    ForeignKey(
      entity = Product::class,
      parentColumns = ["product_sku"],
      childColumns = ["product_sku"],
      onDelete = CASCADE
    )
  ],
  indices = [Index(value = ["product_sku"])]
)
data class Activity(
  @PrimaryKey
  @ColumnInfo("activity_id")
  val activityId: String,

  @ColumnInfo("activity_date")
  val activityDate: Long,

  @ColumnInfo("product_sku")
  val productSku: String,

  @ColumnInfo("amount")
  val amount: Int
)