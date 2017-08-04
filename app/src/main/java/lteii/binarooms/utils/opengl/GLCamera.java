package lteii.binarooms.utils.opengl;

import android.opengl.Matrix;

class GLCamera {


    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    final float[] mvpMatrix = new float[16];

    private float centerX;
    private float centerY;
    float zoomFactor;

    // Cam width = 2*surfaceWHRatio, height = 2
    private final float surfaceHeight;
    private final float surfaceWHRatio;

    GLCamera(int surfaceWidth, int surfaceHeight) {
        this.surfaceHeight = surfaceHeight;
        this.surfaceWHRatio = (float) surfaceWidth / surfaceHeight;

        this.centerX = 0f;
        this.centerY = 0f;
        this.zoomFactor = 1f;

        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        update();
    }


    void move(float x, float y) {
        final float moveScale = 2f/(surfaceHeight*zoomFactor);
        centerX += x*moveScale;
        centerY += y*moveScale;
        update();
    }
    void zoom(float zoom) {
        this.zoomFactor *= zoom;
        update();
    }


    private void update() {
        final float semiWidth = surfaceWHRatio/zoomFactor;
        final float semiHeight = 1f/zoomFactor;
        Matrix.frustumM(projectionMatrix, 0,
                centerX+semiWidth, centerX-semiWidth,
                centerY-semiHeight, centerY+semiHeight,
                3, 7);
        Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
    }


}
