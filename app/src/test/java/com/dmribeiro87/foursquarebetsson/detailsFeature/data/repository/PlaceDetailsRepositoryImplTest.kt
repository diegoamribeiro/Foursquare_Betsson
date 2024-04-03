package com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource.PlaceDetailsDataSource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.mockPlaceDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test


@ExperimentalCoroutinesApi
class PlaceDetailsRepositoryImplTest {

    private val dataSource = mockk<PlaceDetailsDataSource>(relaxed = true)
    private val repository = PlaceDetailsRepositoryImpl(dataSource)
    private val placeId = "123"

    @Test
    fun `when getPlaceDetails is successful, should return success`() = runBlocking {
        val fakeDetails = mockPlaceDetails
        coEvery { dataSource.getPlaceDetails(placeId) } returns Resource.Success(fakeDetails)

        val result = repository.getPlaceDetails(placeId)

        coVerify { dataSource.getPlaceDetails(placeId) }
        assert(result is Resource.Success)
        assertEquals(fakeDetails, result.data)
    }

    @Test
    fun `when getPlaceDetails fails, should return error`() = runBlocking {
        val errorMessage = "Error occurred"
        coEvery { dataSource.getPlaceDetails(placeId) } returns Resource.Error(errorMessage)

        val result = repository.getPlaceDetails(placeId)

        coVerify { dataSource.getPlaceDetails(placeId) }
        assert(result is Resource.Error)
        assertEquals(errorMessage, result.message)
    }
}
