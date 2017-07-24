package lteii._10rooms.utils;


import android.graphics.Canvas;
import android.view.View;

public class Camera {

    public float centerX, centerY;
    public float scale;

    public Camera() {
        this(0f, 0f, 1f);
    }
    public Camera(float centerX, float centerY, float scale) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.scale = scale;
    }

    public void applyToCanvas(Canvas canvas, View canvasHolder) {
        canvas.scale(scale, scale);
        canvas.translate(canvasHolder.getWidth()/2f + centerX*scale, canvasHolder.getHeight()/2f + centerY*scale);
    }
}
