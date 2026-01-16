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
class EditProductPageViewModel @Inject constructor(
  private val productRepository: ProductRepository
): AddEditProductPageViewModel() {
  private var product: Product? = null

  override fun onSubmit(product: Product) {
    viewModelScope.launch {
      try {
        productRepository.updateProduct(product)
        emitUiEvent(AddEditProductPageUiEvent.ShowSnackbar("Product updated successfully!"))
        emitUiEvent(AddEditProductPageUiEvent.OnBackPressed)
      } catch (e: Exception) {
        Log.e("EditProductPageViewModel.onSubmit", e.message.orEmpty())
        emitUiEvent(AddEditProductPageUiEvent.ShowSnackbar("Failed to update product. Try again later"))
      }
    }
  }

  override fun initializeProduct(product: Product) {
    this.product = product
    super.initializeProduct(product)
  }

  fun onDeleteProduct() {
    product?.let {
      viewModelScope.launch {
        try {
          productRepository.deleteProduct(it)
          emitUiEvent(AddEditProductPageUiEvent.ShowSnackbar("Product deleted successfully!"))
          emitUiEvent(AddEditProductPageUiEvent.OnBackPressed)
        } catch (e: Exception) {
          Log.e("EditProductPageViewModel.initializeProduct", e.message.orEmpty())
          emitUiEvent(AddEditProductPageUiEvent.ShowSnackbar("Failed to delete product. Try again later"))
        }
      }
    }
  }

}