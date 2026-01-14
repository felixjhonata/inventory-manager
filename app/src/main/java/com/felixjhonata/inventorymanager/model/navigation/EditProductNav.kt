package com.felixjhonata.inventorymanager.model.navigation

import kotlinx.serialization.Serializable

@Serializable
data class EditProductNav(
  val productSku: String,
  val productName: String,
  val amount: Int
)