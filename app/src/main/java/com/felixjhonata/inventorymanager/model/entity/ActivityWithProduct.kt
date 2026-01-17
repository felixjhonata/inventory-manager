package com.felixjhonata.inventorymanager.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ActivityWithProduct(
  @Embedded
  val activity: Activity,

  @Relation(
    parentColumn = "product_sku",
    entityColumn = "product_sku"
  )
  val product: Product
)