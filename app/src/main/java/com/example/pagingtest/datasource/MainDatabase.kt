package com.example.pagingtest.datasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
   version = MainDatabase.VERSION,
   entities = [
      Item::class
   ]
)
abstract class MainDatabase : RoomDatabase() {

   companion object {
      const val VERSION = 1
   }

   abstract fun itemDao(): ItemDao
}