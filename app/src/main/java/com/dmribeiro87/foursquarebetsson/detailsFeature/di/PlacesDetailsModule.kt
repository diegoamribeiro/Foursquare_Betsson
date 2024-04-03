package com.dmribeiro87.foursquarebetsson.detailsFeature.di

import com.dmribeiro87.foursquarebetsson.core.data.remote.PlacesApi
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource.PlaceDetailsDataSource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource.TipsDataSource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository.PlaceDetailsDataSourceImpl
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository.PlaceDetailsRepositoryImpl
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository.TipsDataSourceImpl
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository.TipsRepositoryImpl
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository.PlaceDetailsRepository
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository.TipsRepository
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase.GetPlaceDetailsUseCase
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase.GetPlaceTipsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlacesDetailsModule {

    @Provides
    @Singleton
    fun provideTipsDataSource(placesApi: PlacesApi): TipsDataSource = TipsDataSourceImpl(placesApi)

    @Provides
    @Singleton
    fun provideTipsRepository(dataSource: TipsDataSource): TipsRepository = TipsRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideGetPlaceTipsUseCase(repository: TipsRepository): GetPlaceTipsUseCase = GetPlaceTipsUseCase(repository)

    @Provides
    @Singleton
    fun providePlacesDetailsDataSource(placesApi: PlacesApi): PlaceDetailsDataSource =
        PlaceDetailsDataSourceImpl(placesApi)

    @Provides
    @Singleton
    fun providePlacesDetailsRepository(dataSource: PlaceDetailsDataSource): PlaceDetailsRepository =
        PlaceDetailsRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideGetPlaceDetailsUseCase(repository: PlaceDetailsRepository): GetPlaceDetailsUseCase =
        GetPlaceDetailsUseCase(repository)
}

