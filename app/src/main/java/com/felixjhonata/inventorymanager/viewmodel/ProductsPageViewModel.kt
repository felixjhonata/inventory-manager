package com.felixjhonata.inventorymanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felixjhonata.inventorymanager.model.ui_model.ProductsPageUiModel
import com.felixjhonata.inventorymanager.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsPageViewModel @Inject constructor(
  private val productRepository: ProductRepository
): ViewModel() {
  private val _uiModel = MutableStateFlow(ProductsPageUiModel())
  val uiModel = _uiModel.asStateFlow()

  fun getProducts() {
    viewModelScope.launch {
      productRepository.getProducts().collect { products ->
        _uiModel.update {
          it.copy(products = products)
        }
      }
    }
  }
}