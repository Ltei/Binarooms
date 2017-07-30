package lteii.binarooms.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;

import java.util.Random;

public class MathUtils {

    public static double RAD_TO_DEG = 180.0/Math.PI;
    public static double DEG_TO_RAD = Math.PI/180.0;


    public static boolean isBrightColor(int color) {
        if (android.R.color.transparent == color) return true;
        final int red = Color.red(color);
        final int green = Color.green(color);
        final int blue = Color.blue(color);
        final int brightness = (int) Math.sqrt(red*red*.241 + green*green*.691 + blue*blue*.068);
        return brightness >= 200;
    }
    public static int randomColor() {
        return Color.argb(255, random(0,255), random(0,255), random(0,255));
    }

    public static float dpToPixels(float dp, Context context){
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    public static float pixelsToDp(float px, Context context){
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float scaleValue(float value, float lastMin, float lastMax, float newMin, float newMax) {
        if (lastMin >= lastMax || newMin >= newMax) throw new IllegalStateException(lastMin+" "+lastMax+" "+newMin+" "+newMax);
        // Scale to [0,1]
        value = (value-lastMin)/(lastMax-lastMin);
        // Scale to [newMin,newMax]
        value = (value*(newMax-newMin))+newMin;
        return value;
    }

    public static int random(int min, int max) {
        if (min > max) throw new IllegalStateException();
        final Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

}
