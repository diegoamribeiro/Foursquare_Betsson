package com.dmribeiro87.foursquarebetsson.placesFeature.di

import android.content.Context
import com.dmribeiro87.foursquarebetsson.core.data.remote.PlacesApi
import com.dmribeiro87.foursquarebetsson.placesFeature.data.datasource.PlacesRemoteDataSource
import com.dmribeiro87.foursquarebetsson.placesFeature.data.repository.LocationServiceImpl
import com.dmribeiro87.foursquarebetsson.placesFeature.data.repository.PlacesDataSourceImpl
import com.dmribeiro87.foursquarebetsson.placesFeature.data.repository.PlacesRepositoryImpl
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.LocationService
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.PlacesRepository
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase.SearchPlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PlacesFeatureModule {

    @Provides
    @Singleton
    fun provideLocationService(@ApplicationContext context: Context): LocationService {
        return LocationServiceImpl(context)
    }

    @Provides
    @Singleton
    fun providePlacesDataSource(service: PlacesApi): PlacesRemoteDataSource {
        return PlacesDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun providePlacesRepository(remoteDataSource: PlacesRemoteDataSource): PlacesRepository {
        return PlacesRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetPopularPlacesUseCase(placesRepository: PlacesRepository): SearchPlacesUseCase {
        return SearchPlacesUseCase(placesRepository)
    }
}