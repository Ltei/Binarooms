/*package lteii.binarooms.utils;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import javax.microedition.khronos.opengles.GL10;

import lteii.binarooms.trash.GLRenderer;
import lteii.binarooms.trash.GLShapeCircleV3;


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
            public void drawShapes(GL10 gl, float[] mvpMatrix) {
                circle.draw(mvpMatrix);
            }
        });
    }

}
*/