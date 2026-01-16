package com.felixjhonata.inventorymanager.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.model.ui_event.AddEditProductPageUiEvent
import com.felixjhonata.inventorymanager.model.ui_model.AddEditProductPageUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class AddEditProductPageViewModel: ViewModel() {
  private val _uiModel = MutableStateFlow(AddEditProductPageUiModel())
  val uiModel = _uiModel.asStateFlow()

  private val _uiEvent = MutableSharedFlow<AddEditProductPageUiEvent>()
  val uiEvent = _uiEvent.asSharedFlow()

  protected abstract fun onSubmit(product: Product)

  fun onSubmitButtonClick() {
    validateProduct()?.let {
      onSubmit(it)
    }
  }

  fun onBackPressed() {
    viewModelScope.launch {
      emitUiEvent(AddEditProductPageUiEvent.OnBackPressed)
    }
  }

  protected suspend fun emitUiEvent(uiEvent: AddEditProductPageUiEvent) {
    _uiEvent.emit(uiEvent)
  }

  open fun initializeProduct(product: Product) {
    _uiModel.update {
      it.copy(
        productSku = product.sku,
        productName = product.productName,
        amount = product.amount.toString()
      )
    }
  }

  private fun allFieldsFilled(): Boolean {
    return with(uiModel.value) {
      val isProductSkuFilled = productSku.isNotEmpty()
      if (!isProductSkuFilled) {
        _uiModel.update {
          it.copy(
            isProductSkuError = true,
            productSkuErrorMsg = "Product sku must be filled!"
          )
        }
      }

      val isProductNameFilled = productName.isNotEmpty()
      if (!isProductNameFilled) {
        _uiModel.update {
          it.copy(
            isProductNameError = true,
            productNameErrorMsg = "Product name must be filled!"
          )
        }
      }

      val isAmountFilled = amount.isNotEmpty()
      if (!isAmountFilled) {
        _uiModel.update {
          it.copy(
            isAmountError = true,
            amountErrorMsg = "Amount must be filled!"
          )
        }
      }

      isProductSkuFilled && isProductNameFilled && isAmountFilled
    }
  }

  protected fun validateProduct(): Product? {
    return with(uiModel.value) {
      if (isProductSkuError || isProductNameError || isAmountError || !allFieldsFilled()) {
        null
      } else {
        Product(
          productSku,
          productName,
          amount.toInt()
        )
      }
    }
  }

  fun onProductSkuValueChange(value: String) {
    _uiModel.update {
      it.copy(
        isProductSkuError = false,
        productSkuErrorMsg = "",
        productSku = value
      )
    }
  }

  fun onProductNameValueChange(value: String) {
    _uiModel.update {
      it.copy(
        isProductNameError = false,
        productNameErrorMsg = "",
        productName = value
      )
    }
  }

  fun onAmountValueChange(value: String) {
    if (value.isDigitsOnly()) {
      _uiModel.update {
        it.copy(
          isAmountError = false,
          amountErrorMsg = ""
        )
      }
    } else {
      _uiModel.update {
        it.copy(
          isAmountError = true,
          amountErrorMsg = "Amount must only consist of digits!"
        )
      }
    }

    _uiModel.update {
      it.copy(amount = value)
    }
  }
}