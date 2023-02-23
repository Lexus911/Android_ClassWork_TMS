package com.example.kotlinapp

import com.example.kotlinapp.data.database.FavoritesEntity
import com.example.kotlinapp.data.database.ItemsEntity
import com.example.kotlinapp.data.database.dao.ItemsDAO
import com.example.kotlinapp.data.items.ItemsRepositoryImpl
import com.example.kotlinapp.data.model.ItemsResponse
import com.example.kotlinapp.data.service.ApiService
import com.example.kotlinapp.data.service.ApiServiceSecond
import com.example.kotlinapp.domain.items.ItemsRepository
import com.example.kotlinapp.model.ItemsModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.createTestCoroutineScope
import okhttp3.Response
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ExampleUnitTest {
   lateinit var itemsRepository: ItemsRepository

   @Mock
   lateinit var apiService: ApiService

   @Mock
   lateinit var apiServiceSecond: ApiServiceSecond

   @Mock
   lateinit var itemsDAO: ItemsDAO

   @Before
   fun setUp(){
       MockitoAnnotations.initMocks((this))
       itemsRepository = ItemsRepositoryImpl(apiService, apiServiceSecond, itemsDAO)
   }

    @Test
    fun `getData request successful`(){
        val response = retrofit2.Response.success((ItemsResponse(listOf())))

       createTestCoroutineScope(TestCoroutineScope().testScheduler).launch {
            Mockito.`when`(apiService.getData()).thenReturn(response)

            itemsRepository.getData()

            verify(apiService, only()).getData()
        }
    }

    @Test
    fun `showData request successful`(){
        val itemsEntity = listOf(ItemsEntity(0, "",""))

        runBlocking {
            `when`(itemsDAO.getItemsEntities()).thenReturn(itemsEntity)
            itemsRepository.showData()
            verify(itemsDAO, times(1)).getItemsEntities()
        }
    }

    @Test
    fun `getData request error`(){
        val response = retrofit2.Response.success((ItemsResponse(listOf())))

        createTestCoroutineScope(TestCoroutineScope().testScheduler).launch {
            Mockito.`when`(apiService.getData()).thenThrow(Exception())

            itemsRepository.getData()

            verify(apiService, only()).getData()
        }
    }

    @Test(expected = Exception::class)
    fun `showData request error`(){
        val itemsEntity = listOf(ItemsEntity(0, "",""))

        runBlocking {
            `when`(itemsDAO.getItemsEntities()).thenThrow(Exception())
            itemsRepository.showData()
            verify(itemsDAO, never()).getItemsEntities()
        }
    }

    @Test
    fun `deleteItemByDescription success`(){
        runBlocking {
            doNothing().`when`(itemsDAO.deleteItemEntityByDescription("descr"))
            itemsRepository.deleteItemByDescription("descr")
            verify(itemsDAO, times(1)).deleteItemEntityByDescription("descr")
        }
    }

    @Test
    fun `getFavorites success`(){
        val favEntity = listOf<FavoritesEntity>()
        runBlocking {
            `when`(itemsDAO.getFavoritesEntities()).thenReturn(favEntity)
            itemsRepository.getFavorites()
            verify(itemsDAO, times(1)).getFavoritesEntities()
            assertEquals(favEntity, itemsDAO.getFavoritesEntities())
            assertNotSame(itemsDAO.getItemsEntities(), itemsDAO.getFavoritesEntities())
        }
    }

}