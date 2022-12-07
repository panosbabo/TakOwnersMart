package com.example.takownersmart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MapPage extends Fragment {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    Location currentLocation;
    private final List<LocationsObject> locationList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map_page, container, false);

        // Initialization to load the owners locations on the map
        readLocations();

        // Initializing client location
        client = LocationServices.getFusedLocationProviderClient(this.getContext());

        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        // Check if the user has given permission for the use of the GPS services of the device
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Running get location
            getCurrentLocation();
        }
        // Else request for tracking location permission
        else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);
        }

        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // When map is loaded
                LatLng currentL = new LatLng(53.355482, -6.281508);

                // Markings
                // For Loop that makes one marker on map for each guitar owner
                for(int i = 0; i < locationList.size(); i++) {
                    LatLng posit = positions(locationList.indexOf(i), locationList.get(i).getLatitude(), locationList.get(i).getLongitude());
                    googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon)).position(posit).title(locationList.get(i).getOwnerName() + locationList.get(i).getGuitarmodelOwner()));
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentL, 12));

                // This adds markers when user taps on map
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        // When clicked on map

                        // Initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                        // Set position of marker
                        markerOptions.position(latLng);
                        // Set title of marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        // Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                        // Add marker on map
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });

        // Return view
        return view;
    }

    // private Function that fetches strings of latitude and longitude in csv, translates and returns it to LatLng variable
    private LatLng positions (int pos, String lat, String longt) {
        LatLng listPosition;
        listPosition = new LatLng (Double.parseDouble(lat), Double.parseDouble(longt));

        return listPosition;
    }

    // Function that reads and fetches all locations of guitar owners from a csv file
    private void readLocations() {
        InputStream is = getResources().openRawResource(R.raw.guitar_list_locations);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        String line = "";
        try {
            while((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");

                LocationsObject location_list = new LocationsObject(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        tokens[3]
                );
                locationList.add(location_list);
            }
        }
        catch (IOException e) {
            Log.wtf("My activity", "Error reading line " + line, e);
            e.printStackTrace();
        }
    }

    // Function that gets current location
    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //when success
                    if (location != null) {
                        currentLocation = location;
                        //sync map
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                // When map is loaded
                                LatLng currentL = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                                googleMap.addMarker(new MarkerOptions().position(currentL).title("Here I am!"));
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentL, 10));
                            }
                        });
                    }
                }
            });
        }
    }

    // onRequestPermissionResult if the request code was received to getCurrentLocation()
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 99) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // When permission is granted
                getCurrentLocation();
            }
        }
    }

}

// Class for location parsing from the csv file
class LocationsObject {

    // Private Variables
    private String latitude;
    private String longitude;
    private String ownerName;
    private String guitarmodelOwner;

    // Constructor
    public LocationsObject(String ownerName, String guitarmodelOwner, String latitude, String longitude) {
        this.ownerName = ownerName;
        this.guitarmodelOwner = guitarmodelOwner;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    // Getters and Setters
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getGuitarmodelOwner() {
        return guitarmodelOwner;
    }

    public void setGuitarmodelOwner(String guitarmodelOwner) {
        this.guitarmodelOwner = guitarmodelOwner;
    }
}