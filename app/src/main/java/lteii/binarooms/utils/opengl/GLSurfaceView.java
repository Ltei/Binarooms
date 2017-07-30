package lteii.binarooms.utils.opengl;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class GLSurfaceView extends android.opengl.GLSurfaceView {

    private final GLRenderer mRenderer;

    public GLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        mRenderer = new GLRenderer();
        setRenderer(mRenderer);
        setRenderMode(android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public GLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setEGLContextClientVersion(2);
        mRenderer = new GLRenderer();
        setRenderer(mRenderer);
        setRenderMode(android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            float dx = x - mPreviousX;
            float dy = y - mPreviousY;
            if (y > getHeight() / 2) dx = dx * -1 ;
            if (x < getWidth() / 2) dy = dy * -1 ;
            mRenderer.setAngle(mRenderer.getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR));
            requestRender();
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}

