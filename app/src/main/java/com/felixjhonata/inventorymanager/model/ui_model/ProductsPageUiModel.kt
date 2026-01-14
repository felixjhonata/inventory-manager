package com.felixjhonata.inventorymanager.model.ui_model

import com.felixjhonata.inventorymanager.model.entity.Product

data class ProductsPageUiModel(
  val products: List<Product> = emptyList()
)