package lteii._10rooms;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static lteii._10rooms.ActMain.RC_CHECK_PERMISSIONS;

public class Permissions {

    public static boolean checkup(Activity parentActivity) {
        if (ContextCompat.checkSelfPermission(parentActivity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        /*if (ActivityCompat.shouldShowRequestPermissionRationale(parentActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return;
        }*/
        ActivityCompat.requestPermissions(parentActivity, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, RC_CHECK_PERMISSIONS);
        return false;
    }

}
