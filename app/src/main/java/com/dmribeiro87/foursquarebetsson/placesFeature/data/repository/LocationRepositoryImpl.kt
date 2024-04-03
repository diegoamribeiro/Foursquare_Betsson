package com.dmribeiro87.foursquarebetsson.placesFeature.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Location
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationServiceImpl @Inject constructor(
    private val context: Context
): LocationService {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    override suspend fun getCurrentLocation(): Resource<Location> = withContext(Dispatchers.IO) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return@withContext Resource.Error("Location permission not granted", null)
        }

        try {
            val task = fusedLocationClient.lastLocation
            val location = Tasks.await(task)
            if (location != null) {
                // Mapeia android.location.Location para o modelo de domínio Location
                val mappedLocation = Location(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                Resource.Success(mappedLocation)
            } else {
                Resource.Error("Location is null", null)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error occurred", null)
        }
    }
}

