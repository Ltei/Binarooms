package lteii.binarooms.utils.opengl;


import android.graphics.Color;

public class GLColor {

    public final float[] rgba;

    private GLColor(float r, float g, float b, float a) {
        if (r < 0f || r > 1f) throw new IllegalArgumentException();
        if (g < 0f || g > 1f) throw new IllegalArgumentException();
        if (b < 0f || b > 1f) throw new IllegalArgumentException();
        if (a < 0f || a > 1f) throw new IllegalArgumentException();
        rgba = new float[] {r,g,b,a};
    }

    public static GLColor rgba(float r, float g, float b, float a) {
        return new GLColor(r, g, b, a);
    }

    public static GLColor fromColor(int color) {
        float r = Color.red(color);
        float g = Color.green(color);
        float b = Color.blue(color);
        float a = Color.alpha(color);
        return new GLColor(r/255f, g/255f, b/255f, a/255f);
    }



}
