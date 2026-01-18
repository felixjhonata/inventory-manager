package com.felixjhonata.inventorymanager.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.felixjhonata.inventorymanager.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.NumberFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class ActivitiesPageViewModel @Inject constructor(
  val activityRepository: ActivityRepository
): ViewModel() {
  val activities = Pager(
    config = PagingConfig(pageSize = 20)
  ) { activityRepository.getActivitiesWithProduct() }.flow.cachedIn(viewModelScope)

  @RequiresApi(Build.VERSION_CODES.O)
  fun formatMillisDateTime(millis: Long): String {
    val formatter = DateTimeFormatter.ofPattern("d MMM yyyy")
    val instant = Instant.ofEpochMilli(millis)
    val date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()

    return date.format(formatter)
  }

  fun formatNumber(amount: Int): String {
    val formatter = NumberFormat.getInstance()
    return formatter.format(amount.absoluteValue)
  }
}