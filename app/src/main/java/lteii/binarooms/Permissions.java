package lteii.binarooms;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

import static lteii.binarooms.ActMain.RC_CHECK_PERMISSIONS;

public class Permissions {

    public static boolean checkup(Activity parentActivity) {
        final ArrayList<String> requests = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(parentActivity, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
            requests.add(Manifest.permission.INTERNET);
        if (ContextCompat.checkSelfPermission(parentActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requests.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (requests.size() == 0) {
            return true;
        } else {
            ActivityCompat.requestPermissions(parentActivity, requests.toArray(new String[requests.size()]), RC_CHECK_PERMISSIONS);
            return false;
        }
    }



}
