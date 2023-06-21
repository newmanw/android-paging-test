package com.example.pagingtest.datasource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "id")
   val id: Int,

   @ColumnInfo(name = "name")
   val name: String
)