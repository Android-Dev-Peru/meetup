package pe.com.peruapps.meetup.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static pe.com.peruapps.meetup.MeetUp.getAppContext;

public class Helper {
    public static final int MY_SOCKET_TIMEOUT_MS = 30000;

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getAppContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / 160.0F));
    }

    public static double distance(double meLatitude, double meLongitude, double driverLatitude, double driverLongitude, String unit) {
        double theta = meLongitude - driverLongitude;
        double dist = Math.sin(deg2rad(meLatitude)) * Math.sin(deg2rad(driverLatitude)) + Math.cos(deg2rad(meLatitude)) * Math.cos(deg2rad(driverLatitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit.equals("K")) {
            dist = dist * 1.609344;
        } else if (unit.equals("N")) {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public static Date stringToDate(String string) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        Date date = null;

        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}