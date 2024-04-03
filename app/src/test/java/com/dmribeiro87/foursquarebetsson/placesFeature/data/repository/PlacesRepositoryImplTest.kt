package com.dmribeiro87.foursquarebetsson.placesFeature.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.Category
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.Icon
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.Location
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PhotoResponse
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PlacesResponse
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.Result
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.core.util.Resource.*
import com.dmribeiro87.foursquarebetsson.core.util.Utils
import com.dmribeiro87.foursquarebetsson.placesFeature.data.mockPlaceList
import com.dmribeiro87.foursquarebetsson.placesFeature.data.datasource.PlacesRemoteDataSource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PlacesRepositoryImplTest {

    private val mockPlacesRemoteDataSource = mockk<PlacesRemoteDataSource>()
    private val mockContext = mockk<Context>(relaxed = true)

    private lateinit var placesRepositoryImpl: PlacesRepositoryImpl

    private val location = "mockLocation"

    @Before
    fun setUp() {
        mockkStatic(Utils::class)
        every { Utils.hasInternetConnection(mockContext) } returns true

        placesRepositoryImpl = PlacesRepositoryImpl(placesRemoteDataSource = mockPlacesRemoteDataSource)
    }

    @Test
    fun `searchNearbyPlaces returns success when data is available`() = runTest {
        val mockPlacesList = mockPlaceList

        coEvery {
            mockPlacesRemoteDataSource.searchNearbyPlaces(
                location = any(),
                categories = any(),
                radius = any(),
                minPrice = any(),
                maxPrice = any(),
                openNow = any(),
                fields = any()
            )
        } returns Response.success(PlacesResponse(null, listOf()))

        // Ação
        val result = placesRepositoryImpl.searchNearbyPlaces(location, 1, 4, false)

        // Asserção
        assertEquals(mockPlacesList, result.data)
    }

    @After
    fun tearDown() {
        unmockkStatic(Utils::class)
    }
}

