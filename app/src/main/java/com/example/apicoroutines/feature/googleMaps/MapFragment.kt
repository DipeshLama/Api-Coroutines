package com.example.apicoroutines.feature.googleMaps

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentMapBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import java.util.*
import kotlin.collections.ArrayList

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding
    private var mMap: GoogleMap? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val args: MapFragmentArgs by navArgs()
    private val REQUEST_CHECK_SETTINGS = 10001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        turnOnLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_map,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPermissionList()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapLayout) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(google: GoogleMap) {
        mMap = google
        val longitude = args.longitude.toDouble()
        val latitude = args.latitude.toDouble()
        addMarker(LatLng(latitude, longitude))
        setLocation(LatLng(latitude, longitude))
        onMapListener()
        init()
    }

    private fun getDeviceLocation() {
        val location = fusedLocationProviderClient.lastLocation
        mMap?.isMyLocationEnabled = true
        location.addOnSuccessListener {
            if (args.longitude.isEmpty() && args.latitude.isEmpty()) {
                addMarker(LatLng(it.latitude, it.longitude))
            }
        }
    }

    private fun moveCamera(latLng: LatLng, zoom: Float) {
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }

    private fun addMarker(latLng: LatLng) {
        mMap?.clear()
        mMap?.addMarker(MarkerOptions().position(LatLng(latLng.latitude, latLng.longitude)))
        moveCamera(latLng, 15f)
    }

    private fun setLocation(latLng: LatLng) {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        binding.edtMapAddress.setText(address[0].getAddressLine(0))
    }

    private fun onMapListener() {
        mMap?.setOnMapClickListener {
            addMarker(it)
            setLocation(it)
        }
    }

    private fun init() {
        binding.edtSearchAddress.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                keyEvent?.action == KeyEvent.ACTION_DOWN ||
                keyEvent?.action == KeyEvent.KEYCODE_ENTER
            ) {
                geoLocate()
                hideSoftKeyboard()
            }
            return@setOnEditorActionListener false
        }
    }

    private fun geoLocate() {
        val geoCoder = Geocoder(requireContext())
        val list = ArrayList<Address>()
        list.addAll(geoCoder.getFromLocationName(binding.edtSearchAddress.text.toString()
            .trim(), 1))

        if (list.isNotEmpty()) {
            val address: Address = list[0]
            addMarker(LatLng(address.latitude, address.longitude))
        }
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                getDeviceLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                getDeviceLocation()
            }
            else -> initPermissionList()
        }
    }

    private fun initPermissionList() {
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    private fun turnOnLocation() {
        val locationRequest = LocationRequest.create().apply {
            priority = Priority.PRIORITY_HIGH_ACCURACY
            interval = 5000
            fastestInterval = 2000
        }
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        builder.setAlwaysShow(true)
        val result: Task<LocationSettingsResponse> = LocationServices.getSettingsClient(
            requireContext())
            .checkLocationSettings(builder.build())

        result.addOnCompleteListener {
            try {
                val response: LocationSettingsResponse = it.getResult(ApiException::class.java)

            } catch (ex: ApiException) {
                when (ex.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = ex as ResolvableApiException
                        resolvableApiException.startResolutionForResult(requireActivity(),
                            REQUEST_CHECK_SETTINGS)

                    } catch (ex: IntentSender.SendIntentException) {
                        ex.printStackTrace()
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
                }
            }
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode === REQUEST_CHECK_SETTINGS) {
//            when (resultCode) {
//                Activity.RESULT_OK -> {
//                    Toast.makeText(requireContext(), "GPS is turned on", Toast.LENGTH_SHORT).show()
//                }
//                Activity.RESULT_CANCELED -> Toast.makeText(requireContext(),
//                    "GPS required to be turned on",
//                    Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun hideSoftKeyboard(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}