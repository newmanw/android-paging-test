package com.example.pagingtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.pagingtest.datasource.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

sealed class ListItem {
   data class Item(val item : com.example.pagingtest.datasource.Item) : ListItem()
   data class HeaderItem(val header : String) : ListItem()
}

@HiltViewModel
class MainViewModel @Inject constructor(
   private val repository: MainRepository
): ViewModel() {

   val items: Flow<PagingData<ListItem>> = Pager(
      config = PagingConfig(pageSize = 20),
      pagingSourceFactory = { repository.observeItems()!! }
   )
   .flow.map { pagingData ->
      pagingData
         .map { ListItem.Item(it) }
         .insertSeparators { before, _ ->
            when {
               // Add a dashed String separator if the prior item is a multiple of 10
               before?.item?.name == "0" -> ListItem.HeaderItem("---------------------")
               // Return null to avoid adding a separator between two items.
               else -> null
            }
      }
   }.cachedIn(viewModelScope)
}
