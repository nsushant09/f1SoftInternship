package com.neupanesushant.mapsintegration

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.neupanesushant.mapsintegration.databinding.ActivityMainBinding
import java.util.*


const val ERROR_LATITUDE: Double = -92138746.0;
const val ERROR_LONGITUDE: Double = -98231746.0;

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var marker1: MarkerOptions
    private lateinit var marker2: MarkerOptions

    val listOfLocations = listOf<LatLng>(
        LatLng(27.71, 85.32),
        LatLng(28.20, 83.98),
        LatLng(28.9985, 83.8473),
        LatLng(28.3685, 83.5390),
        LatLng(28.0548, 81.6145),
        LatLng(27.4291, 87.7682),
        LatLng(29.5239, 82.0788),
        LatLng(29.9363, 80.8987),
        LatLng(28.6852, 80.6216),
        LatLng(27.9881, 86.9250),
        LatLng(27.9207, 82.7347),
        LatLng(27.5341, 84.4525),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setMapType()
        setupSearchFunctionality()

        binding.btnMarkers.setOnClickListener {
            openMarkers()
        }
    }

    fun setupSearchFunctionality() {
        binding.layoutSearch.setEndIconOnClickListener {
            performChangesOnSearch(it)
        }

        binding.etSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    performChangesOnSearch(textView)
                    true
                }
                else -> false
            }
        }

    }

    fun getDistance(): Float {
        val results = FloatArray(1)
        Location.distanceBetween(
            marker1.position.latitude,
            marker1.position.longitude,
            marker2.position.latitude,
            marker2.position.longitude,
            results
        )
        return results.get(0) / 1000
    }

    fun performChangesOnSearch(view: View) {
        val inputText = binding.etSearch.text.toString()
        val latLong = getLatitudeLongitude(inputText)
        if (latLong.latitude == ERROR_LATITUDE || latLong.longitude == ERROR_LONGITUDE) {
            Toast.makeText(applicationContext, "Invalid location", Toast.LENGTH_SHORT).show()
        } else {
            gotoNewPlace(latLong.latitude, latLong.longitude)
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            binding.etSearch.clearFocus()
        }
    }


    fun gotoNewPlace(latitude: Double, longitude: Double) {
        val newLocation = LatLng(latitude, longitude)
        mMap.clear()
        mMap.animateCamera(CameraUpdateFactory.newLatLng(newLocation))
        marker2 = marker1
        marker1 = MarkerOptions().position(newLocation).title(getCityName(latitude, longitude))
        mMap.addMarker(marker1)
        Toast.makeText(
            applicationContext,
            "The distance is : " + getDistance().toString() + "km",
            Toast.LENGTH_LONG
        ).show()
    }

    fun getCityName(lat: Double, long: Double): String {
        val geoCoder = Geocoder(baseContext, Locale.getDefault())
        val cityName: String
        try {
            val Address = geoCoder.getFromLocation(lat, long, 1)
            cityName = Address.get(0).locality
            return cityName
        } catch (e: Exception) {
            return "error"
        }
    }

    fun getLatitudeLongitude(cityName: String): LatitudeLongitude {
        val geoCoder = Geocoder(baseContext, Locale.getDefault())
        try {
            val addressList = geoCoder.getFromLocationName(cityName, 1)
            if (addressList != null && addressList.size > 0) {
                val address = addressList.get(0) as Address
                return LatitudeLongitude(address.latitude, address.longitude)
            }
        } catch (e: Exception) {
        }
        return LatitudeLongitude(ERROR_LATITUDE, ERROR_LONGITUDE)
    }

    fun getLatLngFromName(cityName: String): LatLng {
        val geoCoder = Geocoder(application, Locale.getDefault())
        try {
            val addressList = geoCoder.getFromLocationName(cityName, 1)
            if (addressList != null && addressList.size > 0) {
                val address = addressList.get(0) as Address
                return LatLng(address.latitude, address.longitude)
            }
        } catch (e: Exception) {
        }
        return LatLng(ERROR_LATITUDE, ERROR_LONGITUDE)
    }

    fun setMapType() {
        binding.apply {
            btnDefault.setOnClickListener {
                onMapReady(mMap)
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                binding.layoutSearch.visibility = View.VISIBLE
            }

            btnSatellite.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                binding.layoutSearch.visibility = View.VISIBLE
            }

            btnHybrid.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                binding.layoutSearch.visibility = View.VISIBLE

            }

            btnTerrain.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                binding.layoutSearch.visibility = View.VISIBLE
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.clear()
        mMap.resetMinMaxZoomPreference()
        val position1 = LatLng(55.7, 57.1)
        marker1 = MarkerOptions().position(position1)
            .title(getCityName(position1.latitude, position1.longitude))
        marker2 = marker1
        mMap.addMarker(marker1)
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.animateCamera(CameraUpdateFactory.newLatLng(position1))

        mMap.setOnMapClickListener {
            if(binding.layoutSearch.visibility == View.VISIBLE){
                mMap.clear()
                marker2 = marker1
                marker1 = MarkerOptions().position(it).title(getCityName(it.latitude, it.longitude))
                mMap.addMarker(marker2)
                mMap.addMarker(marker1)
                mMap.animateCamera(CameraUpdateFactory.newLatLng(it))
                Toast.makeText(
                    applicationContext,
                    "The distance is : " + getDistance().toString() + "km",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        markerClick()
    }


    fun setMarkers(latlng: LatLng) {
        mMap.addMarker(
            MarkerOptions().position(latlng).title(getCityName(latlng.latitude, latlng.longitude))
        )
    }

    fun openMarkers() {
        binding.layoutSearch.visibility = View.GONE
        mMap.clear()
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        marker1 = MarkerOptions()
        marker2 = MarkerOptions()
        mMap.animateCamera(CameraUpdateFactory.newLatLng(listOfLocations.get(0)))
        mMap.setMinZoomPreference(8.0f)
        listOfLocations.forEach {
            setMarkers(it)
        }

    }

    fun markerClick() {
        mMap.setOnMarkerClickListener { marker ->
            marker2 = marker1
            marker1 = MarkerOptions().position(marker.position).title(marker.title)

            if(marker2.position != null){
                Toast.makeText(
                    applicationContext,
                    "The distance is : " + getDistance().toString() + "km",
                    Toast.LENGTH_LONG
                ).show()
            }
            true
        }
    }
}