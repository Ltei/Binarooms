package lteii.binarooms.utils;

public class Vector2 {


    public float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public double angleDeg() {
        double angle = Math.atan2(y, x) * MathUtils.RAD_TO_DEG;
        if (angle < 0) angle += 360.0;
        return angle;
    }
    public double angleRad() {
        double angle = Math.atan2(y, x);
        if (angle < 0) angle += 2.0*Math.PI;
        return angle;
    }


}
