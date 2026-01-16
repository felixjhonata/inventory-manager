package com.felixjhonata.inventorymanager.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.model.ui_event.AddEditProductPageUiEvent
import com.felixjhonata.inventorymanager.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductPageViewModel @Inject constructor(
  private val productRepository: ProductRepository
): AddEditProductPageViewModel() {

  override fun onSubmit(product: Product) {
    viewModelScope.launch {
      try {
        productRepository.insertProduct(product)
        emitUiEvent(AddEditProductPageUiEvent.ShowSnackbar("Product Inserted Successfully!"))
        emitUiEvent(AddEditProductPageUiEvent.OnBackPressed)
      } catch (e: Exception) {
        Log.e("AddProductPageViewModelError", e.message.orEmpty())
        emitUiEvent(AddEditProductPageUiEvent.ShowSnackbar("An error occurred when inserting product!"))
      }
    }
  }

}