package com.dmribeiro87.foursquarebetsson.detailsFeature.usecase

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.mockPlaceDetails
import com.dmribeiro87.foursquarebetsson.placesFeature.data.mockPlace
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository.PlaceDetailsRepository
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase.GetPlaceDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPlaceDetailsUseCaseTest {

    private val placeDetailsRepository = mockk<PlaceDetailsRepository>(relaxed = true)
    private val useCase = GetPlaceDetailsUseCase(placeDetailsRepository)
    private val placeId = "123"


    @Test
    fun `when getPlaceDetails is called, should return data from repository`() = runBlocking {
        coEvery { placeDetailsRepository.getPlaceDetails(placeId) } returns Resource.Success(mockPlaceDetails)

        val result = useCase(placeId)

        coVerify { placeDetailsRepository.getPlaceDetails(placeId) }

        assertTrue(result is Resource.Success)
        assertEquals(mockPlaceDetails, result.data)
    }
}