package com.felixjhonata.inventorymanager.viewmodel

import androidx.lifecycle.viewModelScope
import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductPageViewModel @Inject constructor(
  private val productRepository: ProductRepository
): AddEditProductPageViewModel() {
  private var product: Product? = null

  override suspend fun onSubmit(product: Product) {
    productRepository.updateProduct(product)
  }

  override fun initializeProduct(product: Product) {
    this.product = product
    super.initializeProduct(product)
  }

  fun onDeleteProduct() {
    product?.let {
      viewModelScope.launch {
        productRepository.deleteProduct(it)
        onBackPressed()
      }
    }
  }

}