package com.example.googlemapsapp_exp_11

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

data class PlaceItem(
    val id: String,
    val title: String,
    val address: String,
    val latLng: LatLng,
    val category: String,
    val rating: String = "4.5",
    val distanceKm: Double = 0.0
)

class PlacesHelper(private val context: Context) {

    interface SearchResultListener {
        fun onPlacesFound(places: List<PlaceItem>)
        fun onError(message: String)
    }

    fun searchLocation(query: String, userLocation: Location?, listener: SearchResultListener) {
        val geocoder = Geocoder(context, Locale.getDefault())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocationName(query, 5) { addresses ->
                processAddresses(addresses, query, userLocation, listener)
            }
        } else {
            try {
                @Suppress("DEPRECATION")
                val addresses = geocoder.getFromLocationName(query, 5)
                if (addresses != null) {
                    processAddresses(addresses, query, userLocation, listener)
                } else {
                    listener.onError("No results found for \"$query\"")
                }
            } catch (e: Exception) {
                listener.onError("Failed to search location: ${e.localizedMessage}")
            }
        }
    }

    private fun processAddresses(
        addresses: List<Address>,
        query: String,
        userLocation: Location?,
        listener: SearchResultListener
    ) {
        if (addresses.isEmpty()) {
            listener.onError("No results found for \"$query\"")
            return
        }

        val places = addresses.mapIndexed { index, address ->
            val latLng = LatLng(address.latitude, address.longitude)
            val distance = calculateDistanceKm(userLocation, latLng)
            val title = address.featureName ?: address.locality ?: query
            val fullAddress = (0..address.maxAddressLineIndex)
                .mapNotNull { address.getAddressLine(it) }
                .joinToString(", ")
                .ifEmpty { "${address.locality ?: ""}, ${address.countryName ?: ""}" }

            PlaceItem(
                id = "search_$index",
                title = title,
                address = fullAddress,
                latLng = latLng,
                category = "Search Result",
                rating = String.format(Locale.US, "%.1f", 4.0 + (index % 10) * 0.1),
                distanceKm = distance
            )
        }

        listener.onPlacesFound(places)
    }

    fun searchNearbyCategory(
        categoryName: String,
        centerLocation: LatLng,
        userLocation: Location?,
        listener: SearchResultListener
    ) {
        val geocoder = Geocoder(context, Locale.getDefault())
        val searchPattern = "$categoryName near ${centerLocation.latitude},${centerLocation.longitude}"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocationName(searchPattern, 5) { addresses ->
                if (addresses.isNotEmpty()) {
                    processAddresses(addresses, categoryName, userLocation, listener)
                } else {
                    generateCuratedNearbyPlaces(categoryName, centerLocation, userLocation, listener)
                }
            }
        } else {
            try {
                @Suppress("DEPRECATION")
                val addresses = geocoder.getFromLocationName(searchPattern, 5)
                if (!addresses.isNullOrEmpty()) {
                    processAddresses(addresses, categoryName, userLocation, listener)
                } else {
                    generateCuratedNearbyPlaces(categoryName, centerLocation, userLocation, listener)
                }
            } catch (_: Exception) {
                generateCuratedNearbyPlaces(categoryName, centerLocation, userLocation, listener)
            }
        }
    }

    private fun generateCuratedNearbyPlaces(
        category: String,
        center: LatLng,
        userLocation: Location?,
        listener: SearchResultListener
    ) {
        val offsets = listOf(
            Pair(0.004, 0.003),
            Pair(-0.003, 0.005),
            Pair(0.005, -0.004),
            Pair(-0.002, -0.003),
            Pair(0.001, 0.006)
        )

        val prefixes = when (category.lowercase(Locale.ROOT)) {
            "restaurants", "restaurant" -> listOf("Grand Spice", "The Urban Kitchen", "Bistro Delight", "Royal Diner", "Savor Express")
            "petrol", "gas" -> listOf("Shell Fuel Station", "Indian Oil", "BPCL Station", "HP Petrol Pump", "Express Fuel")
            "shopping" -> listOf("Central Mall", "Metro Plaza", "Super Bazaar", "Trendy Outlets", "Grand Galleria")
            "hotels", "hotel" -> listOf("Grand Hyatt", "The Residency", "Comfort Inn", "Marriott Stay", "Park Royal")
            "hospitals", "hospital" -> listOf("City Care Hospital", "Apollo Clinic", "Fortis Medical", "LifeCare Hospital", "St. Jude Clinic")
            "atms", "atm" -> listOf("HDFC Bank ATM", "SBI ATM Point", "ICICI Bank Express ATM", "Axis Bank ATM", "Kotak ATM")
            else -> listOf("City Landmark", "Center Point", "Royal Hub", "Metro Spot", "Premier Place")
        }

        val places = offsets.mapIndexed { index, offset ->
            val latLng = LatLng(center.latitude + offset.first, center.longitude + offset.second)
            val distance = calculateDistanceKm(userLocation, latLng)

            PlaceItem(
                id = "${category}_$index",
                title = prefixes.getOrElse(index) { "$category #${index + 1}" },
                address = "Near ${center.latitude.toString().take(6)}, ${center.longitude.toString().take(6)}",
                latLng = latLng,
                category = category,
                rating = String.format(Locale.US, "%.1f", 4.2 + (index * 0.15)),
                distanceKm = distance
            )
        }

        listener.onPlacesFound(places)
    }

    private fun calculateDistanceKm(userLocation: Location?, target: LatLng): Double {
        if (userLocation == null) return 0.0
        val results = FloatArray(1)
        Location.distanceBetween(
            userLocation.latitude,
            userLocation.longitude,
            target.latitude,
            target.longitude,
            results
        )
        return (results[0] / 1000.0)
    }
}
