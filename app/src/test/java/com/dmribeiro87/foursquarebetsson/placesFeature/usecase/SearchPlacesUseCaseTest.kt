package com.dmribeiro87.foursquarebetsson.placesFeature.usecase

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.placesFeature.data.mockPlaceList
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Place
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.PlacesRepository
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase.SearchPlacesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchPlacesUseCaseTest {

    private val placesRepository = mockk<PlacesRepository>()
    private val useCase = SearchPlacesUseCase(placesRepository)
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `when searchPlaces is called, should return data from repository`() = TestScope().runTest {
        val fakeLocation = "test_location"
        val fakeMinPrice = 1
        val fakeMaxPrice = 4
        val fakeOpenNow = true
        val fakePlaces = mockPlaceList
        coEvery { placesRepository.searchNearbyPlaces(fakeLocation, fakeMinPrice, fakeMaxPrice, fakeOpenNow) } returns Resource.Success(fakePlaces)

        val result = useCase(fakeLocation, fakeMinPrice, fakeMaxPrice, fakeOpenNow)

        coVerify { placesRepository.searchNearbyPlaces(fakeLocation, fakeMinPrice, fakeMaxPrice, fakeOpenNow) }
        assertTrue(result is Resource.Success)
        assertEquals(fakePlaces, result.data)
    }
}
