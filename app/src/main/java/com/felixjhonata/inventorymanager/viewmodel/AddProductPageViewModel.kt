package com.felixjhonata.inventorymanager.viewmodel

import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddProductPageViewModel @Inject constructor(
  private val productRepository: ProductRepository
): AddEditProductPageViewModel() {

  override suspend fun onSubmit(product: Product) {
    productRepository.insertProduct(product)
  }

}