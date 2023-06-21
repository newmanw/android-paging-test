package com.example.pagingtest.datasource

import androidx.paging.PagingSource
import com.example.pagingtest.LocalDataSource
import javax.inject.Inject

class MainRepository @Inject constructor(
   private val localDataSource: LocalDataSource,
) {
   fun observeItems(): PagingSource<Int, Item> = localDataSource.observeItems()
}