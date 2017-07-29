package lteii.binarooms.utils;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import lteii.binarooms.utils.opengl.GLRenderer;
import lteii.binarooms.utils.opengl.GLShapeCircleV3;


public class NavigableViewV2 extends GLSurfaceView {

    public NavigableViewV2(Context context) {
        super(context);
        init();
    }
    public NavigableViewV2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void init() {
        setEGLContextClientVersion(2);

        final GLShapeCircleV3 circle = new GLShapeCircleV3();

        setRenderer(new GLRenderer() {
            @Override
            public void drawShapes(float[] mvpMatrix) {
                circle.draw(mvpMatrix);
            }
        });
    }

}
