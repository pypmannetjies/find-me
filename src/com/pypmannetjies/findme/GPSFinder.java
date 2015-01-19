package com.pypmannetjies.findme;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;

public class GPSFinder {

    private Context context;
    private SmsSender smsSender;
    private LocationListener locationListener;
    private LocationManager locationManager;

    public GPSFinder(Context context, SmsSender smsSender) {
        this.context = context;
        this.smsSender = smsSender;
        initLocationManager();
    }

    private void initLocationManager() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new GPSLocationListener(this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
    }

    public void locationFound(String cityName, double latitude, double
            longitude) {
        String message = getGPSMessage(cityName, latitude, longitude);
        if (latitude != 0 && longitude != 0) {
            smsSender.sendSMS(message);
            locationManager.removeUpdates(locationListener);
        }
    }

    private String getGPSMessage(String cityName, double latitude, double longitude) {
        String s = "Position update triggered near " + cityName
                + ". Coordinates: Latitude " + latitude
                + ", Longtitude " + longitude;

        s += ". Tap this link to open the location in Google Maps http://maps.google.com/?q="
                + latitude + "," + longitude;
        return s;
    }


    public Context getContext() {
        return context;
    }
}
