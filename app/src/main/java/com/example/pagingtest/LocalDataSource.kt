package com.example.pagingtest

import com.example.pagingtest.datasource.ItemDao

class LocalDataSource constructor(
   private val dao: ItemDao
) {
   fun observeItems() = dao.observeItems()
}