package com.example.pagingtest

import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import androidx.paging.testing.asSnapshot
import com.example.pagingtest.datasource.Item
import com.example.pagingtest.datasource.MainRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

   @Test
   fun testPagingTransformation() = runTest {
      val scope = this
      val repository = mockk<MainRepository>()
      every { repository.observeItems() } answers {
         val flow = flowOf(
            listOf(
               Item(
                  id = 0,
                  name = "0"
               ),
               Item(
                  id = 1,
                  name = "1"
               )
            )
         )

         val pagingSourceFactory = flow.asPagingSourceFactory(coroutineScope = scope)
         pagingSourceFactory()
      }

      val viewModel = MainViewModel(
         repository = repository
      )

      val items: Flow<PagingData<ListItem>> = viewModel.items
      val snapshot: List<ListItem> = items.asSnapshot()
      Assert.assertEquals(2, snapshot.size)
   }
}