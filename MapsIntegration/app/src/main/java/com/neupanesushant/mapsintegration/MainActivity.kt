package com.neupanesushant.mapsintegration

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.neupanesushant.mapsintegration.databinding.ActivityMainBinding
import java.io.IOException
import java.lang.NullPointerException
import java.util.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback  {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setMapType()
        setupSearchFunctionality()

    }

    fun setupSearchFunctionality(){
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

    fun getDistance(){
//        Location.distanceBetween()
        
    }

    fun performChangesOnSearch(view : View){
        val inputText = binding.etSearch.text.toString()
        val latLong = getLatitudeLongitude(inputText)
        gotoNewPlace(latLong.latitude, latLong.longitude)
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        binding.etSearch.clearFocus()
    }


    fun gotoNewPlace(latitude : Double, longitude : Double){
        val newLocation = LatLng(latitude, longitude)
        mMap.clear()
        mMap.animateCamera(CameraUpdateFactory.newLatLng(newLocation))
        mMap.addMarker(MarkerOptions()
            .position(newLocation)
            .title(getCityName(latitude, longitude))
        )
    }

    fun getCityName(lat: Double, long: Double): String {
        val cityName: String
        val geoCoder = Geocoder(application, Locale.getDefault())
        try{
            val Address = geoCoder.getFromLocation(lat, long, 1)
            cityName = Address.get(0).locality
            return cityName
        }catch (e : NullPointerException){
        }
        return "nullValue"
    }

    fun getLatitudeLongitude(cityName : String) : LatitudeLongitude{
        val geoCoder = Geocoder(application, Locale.getDefault())
        var result : String? = null
        try {
            val addressList = geoCoder.getFromLocationName(cityName, 1)
            if (addressList != null && addressList.size > 0) {
                val address = addressList.get(0) as Address
                return LatitudeLongitude(address.latitude, address.longitude)
            }
        } catch (e: IOException) {
            Log.e("TAG", "Unable to connect to GeoCoder")
        }
        return LatitudeLongitude(0.0, 0.0)
    }

    fun setMapType(){
        binding.apply {
            btnDefault.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }

            btnSatellite.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }

            btnHybrid.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }

            btnTerrain.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(27.71, 85.32)
        mMap.addMarker(MarkerOptions()
            .position(sydney)
            .title("Marker is set at latitude : ${sydney.latitude} and longitude : ${sydney.longitude}")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.setOnMapClickListener{
            mMap.clear()
            mMap.addMarker(MarkerOptions()
                .position(it)
                .title("New marker set"))
        }
    }
}