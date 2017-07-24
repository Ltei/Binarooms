package lteii._10rooms.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;

import lteii._10rooms.R;

public class Utils {

    public static void showWarning(Context context, String text) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTheme);
        builder.setTitle(text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public static String getPath(Context context, Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    public static String formatUInteger(int value, int stringLength) {
        if (value < 0) throw new IllegalArgumentException();
        if (stringLength <= 0) throw new IllegalArgumentException();

        String string = String.valueOf(value);

        final int deltaLength = stringLength - string.length();
        if (deltaLength < 0) throw new IllegalStateException();

        for (int i=0; i<deltaLength; i++) {
            string = "0"+string;
        }
        return string;
    }

}
