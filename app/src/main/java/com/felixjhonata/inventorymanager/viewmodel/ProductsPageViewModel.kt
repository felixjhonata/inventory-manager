package com.felixjhonata.inventorymanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.felixjhonata.inventorymanager.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsPageViewModel @Inject constructor(
  private val productRepository: ProductRepository
): ViewModel() {
  val products = Pager(
    PagingConfig(pageSize = 20)
  ) { productRepository.getProducts() }.flow.cachedIn(viewModelScope)
}