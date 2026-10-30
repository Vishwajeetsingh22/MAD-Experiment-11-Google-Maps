package com.example.googlemapsapp_exp_11

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val studentName = "vishwajeet singh"
    private val studentUSN = "25MCAR0219"

    private lateinit var webViewMap: WebView
    private lateinit var locationHelper: LocationHelper

    private var initialGpsLocationSet = false

    // UI Elements
    private lateinit var etSearchCity: EditText
    private lateinit var btnSearch: MaterialButton

    private lateinit var btnMapNormal: MaterialButton
    private lateinit var btnMapSatellite: MaterialButton
    private lateinit var btnMapTerrain: MaterialButton
    private lateinit var btnMapHybrid: MaterialButton

    private lateinit var chipBengaluru: MaterialButton
    private lateinit var chipNewDelhi: MaterialButton
    private lateinit var chipMumbai: MaterialButton
    private lateinit var chipTajMahal: MaterialButton
    private lateinit var chipRanchi: MaterialButton

    private lateinit var tvTopOverlayText: TextView
    private lateinit var tvBottomOverlayInfo: TextView
    private lateinit var tvBottomOverlayLocation: TextView

    private lateinit var cardZoomIn: MaterialCardView
    private lateinit var cardZoomOut: MaterialCardView
    private lateinit var fabMyLocation: FloatingActionButton

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

            if (fineGranted || coarseGranted) {
                startRealtimeLocationTracking()
                recenterToDeviceGpsLocation()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)

        locationHelper = LocationHelper(this)

        initViews()
        setupWebView()
        setupListeners()
    }

    private fun initViews() {
        webViewMap = findViewById(R.id.webViewMap)
        etSearchCity = findViewById(R.id.etSearchCity)
        btnSearch = findViewById(R.id.btnSearch)

        btnMapNormal = findViewById(R.id.btnMapNormal)
        btnMapSatellite = findViewById(R.id.btnMapSatellite)
        btnMapTerrain = findViewById(R.id.btnMapTerrain)
        btnMapHybrid = findViewById(R.id.btnMapHybrid)

        chipBengaluru = findViewById(R.id.chipBengaluru)
        chipNewDelhi = findViewById(R.id.chipNewDelhi)
        chipMumbai = findViewById(R.id.chipMumbai)
        chipTajMahal = findViewById(R.id.chipTajMahal)
        chipRanchi = findViewById(R.id.chipRanchi)

        tvTopOverlayText = findViewById(R.id.tvTopOverlayText)
        tvBottomOverlayInfo = findViewById(R.id.tvBottomOverlayInfo)
        tvBottomOverlayLocation = findViewById(R.id.tvBottomOverlayLocation)

        cardZoomIn = findViewById(R.id.cardZoomIn)
        cardZoomOut = findViewById(R.id.cardZoomOut)
        fabMyLocation = findViewById(R.id.fabMyLocation)

        tvBottomOverlayInfo.text = "USN: $studentUSN | Name: $studentName"
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webViewMap.settings.javaScriptEnabled = true
        webViewMap.settings.domStorageEnabled = true
        webViewMap.settings.allowFileAccess = true

        webViewMap.addJavascriptInterface(WebAppInterface(), "Android")

        webViewMap.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                // Initialize default map at Bengaluru
                updateMapLocation("Bengaluru", 12.9716, 77.5946)

                // Request location permissions & tracking
                if (locationHelper.hasLocationPermissions()) {
                    startRealtimeLocationTracking()
                    recenterToDeviceGpsLocation()
                } else {
                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            }
        }

        val htmlContent = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
                <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
                <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
                <style>
                    html, body, #map { height: 100%; margin: 0; padding: 0; background: #e5e3df; }
                    .leaflet-popup-content-wrapper { border-radius: 8px; font-family: sans-serif; }
                    .leaflet-control-attribution { font-size: 10px; }
                </style>
            </head>
            <body>
                <div id="map"></div>
                <script>
                    var map = L.map('map', { zoomControl: false }).setView([12.9716, 77.5946], 13);
                    
                    // Authentic Google Maps Tile Layers
                    var normalLayer = L.tileLayer('https://mt1.google.com/vt/lyrs=m&x={x}&y={y}&z={z}', {
                        maxZoom: 20,
                        attribution: 'Google Maps'
                    }).addTo(map);

                    var satelliteLayer = L.tileLayer('https://mt1.google.com/vt/lyrs=s&x={x}&y={y}&z={z}', {
                        maxZoom: 20,
                        attribution: 'Google Maps Satellite'
                    });

                    var terrainLayer = L.tileLayer('https://mt1.google.com/vt/lyrs=p&x={x}&y={y}&z={z}', {
                        maxZoom: 20,
                        attribution: 'Google Maps Terrain'
                    });

                    var hybridLayer = L.tileLayer('https://mt1.google.com/vt/lyrs=y&x={x}&y={y}&z={z}', {
                        maxZoom: 20,
                        attribution: 'Google Maps Hybrid'
                    });

                    var activeLayer = normalLayer;
                    var marker = null;

                    function setMapType(type) {
                        map.removeLayer(activeLayer);
                        if (type === 'satellite') {
                            activeLayer = satelliteLayer;
                        } else if (type === 'terrain') {
                            activeLayer = terrainLayer;
                        } else if (type === 'hybrid') {
                            activeLayer = hybridLayer;
                        } else {
                            activeLayer = normalLayer;
                        }
                        activeLayer.addTo(map);
                    }

                    function updateMap(placeName, lat, lng) {
                        map.setView([lat, lng], 14);
                        if (marker) map.removeLayer(marker);
                        
                        var popupText = '<div style="text-align:center; padding:2px; font-family:sans-serif;">' +
                            '<b>USN: $studentUSN</b><br/>' +
                            'Name: $studentName<br/>' +
                            'Place: <b>' + placeName + '</b>' +
                            '</div>';

                        marker = L.marker([lat, lng]).addTo(map)
                            .bindPopup(popupText)
                            .openPopup();
                            
                        Android.onLocationUpdated(placeName, lat, lng);
                    }

                    function zoomIn() { map.zoomIn(); }
                    function zoomOut() { map.zoomOut(); }
                </script>
            </body>
            </html>
        """.trimIndent()

        webViewMap.loadDataWithBaseURL("https://maps.google.com", htmlContent, "text/html", "UTF-8", null)
    }

    private fun setupListeners() {
        // Map Type Buttons (Google Roadmap, Satellite, Terrain, Hybrid)
        btnMapNormal.setOnClickListener { webViewMap.loadUrl("javascript:setMapType('normal')") }
        btnMapSatellite.setOnClickListener { webViewMap.loadUrl("javascript:setMapType('satellite')") }
        btnMapTerrain.setOnClickListener { webViewMap.loadUrl("javascript:setMapType('terrain')") }
        btnMapHybrid.setOnClickListener { webViewMap.loadUrl("javascript:setMapType('hybrid')") }

        // Search Action
        btnSearch.setOnClickListener {
            val query = etSearchCity.text.toString().trim()
            if (query.isNotEmpty()) performSearch(query)
        }

        // Quick City Preset Chips
        chipBengaluru.setOnClickListener { updateMapLocation("Bengaluru", 12.9716, 77.5946) }
        chipNewDelhi.setOnClickListener { updateMapLocation("New Delhi", 28.6139, 77.2090) }
        chipMumbai.setOnClickListener { updateMapLocation("Mumbai", 19.0760, 72.8777) }
        chipTajMahal.setOnClickListener { updateMapLocation("Taj Mahal", 27.1751, 78.0421) }
        chipRanchi.setOnClickListener { updateMapLocation("Ranchi", 23.3441, 85.3096) }

        // Zoom Controls
        cardZoomIn.setOnClickListener { webViewMap.loadUrl("javascript:zoomIn()") }
        cardZoomOut.setOnClickListener { webViewMap.loadUrl("javascript:zoomOut()") }

        // Recenter / My Location FAB
        fabMyLocation.setOnClickListener {
            if (locationHelper.hasLocationPermissions()) {
                recenterToDeviceGpsLocation()
            } else {
                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    private fun recenterToDeviceGpsLocation() {
        Toast.makeText(this, "Acquiring current GPS location…", Toast.LENGTH_SHORT).show()
        locationHelper.fetchFreshLocation { location ->
            runOnUiThread {
                updateMapLocation("My Location", location.latitude, location.longitude)
                Toast.makeText(this, "Centered on GPS location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateMapLocation(placeName: String, lat: Double, lng: Double) {
        webViewMap.loadUrl("javascript:updateMap('$placeName', $lat, $lng)")
        updateLocationOverlays(placeName, lat, lng)
    }

    private fun performSearch(query: String) {
        val geocoder = Geocoder(this, Locale.getDefault())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocationName(query, 1) { addresses ->
                if (addresses.isNotEmpty()) {
                    val addr = addresses.first()
                    val cityName = addr.locality ?: addr.featureName ?: query
                    runOnUiThread { updateMapLocation(cityName, addr.latitude, addr.longitude) }
                } else {
                    runOnUiThread { Toast.makeText(this, "No results found for \"$query\"", Toast.LENGTH_SHORT).show() }
                }
            }
        } else {
            try {
                @Suppress("DEPRECATION")
                val addresses = geocoder.getFromLocationName(query, 1)
                if (!addresses.isNullOrEmpty()) {
                    val addr = addresses.first()
                    val cityName = addr.locality ?: addr.featureName ?: query
                    updateMapLocation(cityName, addr.latitude, addr.longitude)
                } else {
                    Toast.makeText(this, "No results found for \"$query\"", Toast.LENGTH_SHORT).show()
                }
            } catch (_: Exception) {
                Toast.makeText(this, "Search failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateLocationOverlays(placeName: String, lat: Double, lng: Double) {
        val formattedLat = String.format(Locale.US, "%.3f", lat)
        val formattedLng = String.format(Locale.US, "%.3f", lng)

        tvTopOverlayText.text = "Loc: $placeName ($formattedLat, $formattedLng) | USN: $studentUSN | $studentName"
        tvBottomOverlayLocation.text = "Location: $placeName (${String.format(Locale.US, "%.4f", lat)}, ${String.format(Locale.US, "%.4f", lng)})"
    }

    private fun startRealtimeLocationTracking() {
        locationHelper.startLocationUpdates(object : LocationHelper.LocationUpdateListener {
            override fun onLocationReceived(location: Location) {
                if (!initialGpsLocationSet) {
                    initialGpsLocationSet = true
                    updateMapLocation("My Location", location.latitude, location.longitude)
                }
            }

            override fun onLocationError(message: String) {}
        })
    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun onLocationUpdated(placeName: String, lat: Double, lng: Double) {
            runOnUiThread {
                updateLocationOverlays(placeName, lat, lng)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationHelper.stopLocationUpdates()
    }
}
