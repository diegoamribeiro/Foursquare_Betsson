package com.dmribeiro87.foursquarebetsson.detailsFeature.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.mockPlaceDetails
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase.GetPlaceDetailsUseCase
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase.GetPlaceTipsUseCase
import com.dmribeiro87.foursquarebetsson.detailsFeature.presentation.viewmodel.DetailsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private val getPlaceDetailsUseCase = mockk<GetPlaceDetailsUseCase>()
    private val getPlaceTipsUseCase = mockk<GetPlaceTipsUseCase>()
    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailsViewModel(getPlaceDetailsUseCase, getPlaceTipsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `when loadPlaceDetails called, live data should be updated`() {
        val placeId = "123"
        val fakePlaceDetails = mockPlaceDetails
        coEvery { getPlaceDetailsUseCase(placeId) } returns Resource.Success(fakePlaceDetails)

        viewModel.loadPlaceDetails(placeId)

        testScope.advanceUntilIdle()

        coVerify { getPlaceDetailsUseCase(placeId) }
        assertTrue(viewModel.details.getOrAwaitValue() is Resource.Success)
        assertEquals(fakePlaceDetails, viewModel.details.getOrAwaitValue().data)
    }

    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {

            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}
