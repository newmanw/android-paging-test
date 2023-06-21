package com.example.pagingtest.datasource

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import com.example.pagingtest.datasource.Item

@Dao
interface ItemDao {
   @Query("SELECT * FROM items")
   @RewriteQueriesToDropUnusedColumns
   fun observeItems(): PagingSource<Int, Item>
}