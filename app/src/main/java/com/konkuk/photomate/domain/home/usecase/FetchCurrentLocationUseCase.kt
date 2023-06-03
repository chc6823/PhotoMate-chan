package com.konkuk.photomate.domain.home.usecase

import android.content.Context
import android.location.Geocoder
import android.location.Location
import java.util.Locale

class FetchCurrentLocationUseCase(private val context: Context) {

    operator fun invoke(location: Location): String {
        return try {
            val address = Geocoder(context, Locale.KOREA).getFromLocation(
                location.latitude,
                location.longitude,
                1
            )?.firstOrNull()
            if (address != null) {
                "${address.getAddressLine(0)} ${address.countryName} ${address.countryCode} ${address.adminArea} ${address.locality} ${address.thoroughfare} ${address.featureName}"
            } else {
                "Unable to fetch address"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Unable to fetch address"
        }
    }
}
