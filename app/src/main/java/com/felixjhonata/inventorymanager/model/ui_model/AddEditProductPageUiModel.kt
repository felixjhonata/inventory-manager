package com.felixjhonata.inventorymanager.model.ui_model

data class AddEditProductPageUiModel(
  val productSku: String = "",
  val isProductSkuError: Boolean = false,
  val productSkuErrorMsg: String = "",

  val productName: String = "",
  val isProductNameError: Boolean = false,
  val productNameErrorMsg: String = "",

  val amount: String = "",
  val isAmountError: Boolean = false,
  val amountErrorMsg: String = ""
)