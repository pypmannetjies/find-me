package com.pypmannetjies.findme;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Christien on 2015-01-17.
 */
class GPSLocationListener implements LocationListener {

    private GPSFinder gpsFinder;

    public GPSLocationListener(GPSFinder gpsFinder) {
        this.gpsFinder = gpsFinder;
    }

    @Override
    public void onLocationChanged(Location loc) {


        String cityName = "UNKNOWN";

        Geocoder gcd = new Geocoder(gpsFinder.getContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(),
                    loc.getLongitude(), 1);
            if (addresses.size() > 0)
                System.out.println(addresses.get(0).getLocality());
            cityName = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Toast.makeText(gpsFinder.context, gpsFinder.getGPSMessage(), Toast.LENGTH_LONG).show();

        gpsFinder.locationFound(cityName, loc.getLatitude(), loc.getLongitude());
    }


    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
