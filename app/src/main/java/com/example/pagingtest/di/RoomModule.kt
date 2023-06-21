package com.example.pagingtest.di

import android.app.Application
import androidx.room.Room
import com.example.pagingtest.datasource.ItemDao
import com.example.pagingtest.datasource.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

   @Provides
   @Singleton
   fun provideDatabase(application: Application): MainDatabase {
      return Room.databaseBuilder(application.applicationContext, MainDatabase::class.java, "db")
         .fallbackToDestructiveMigration()
         .build()
   }

   @Provides
   @Singleton
   fun provideItemDao(database: MainDatabase): ItemDao {
      return database.itemDao()
   }
}