package lteii.binarooms.utils.opengl;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class GLSurfaceViewNavigable extends GLSurfaceView {


    private static final int INVALID_POINTER_ID = -1;


    private ScaleGestureDetector scaleDetector = null;

    private int activePointerID = INVALID_POINTER_ID;
    private float lastTouchX, lastTouchY;

    public GLSurfaceViewNavigable(Context context) {
        super(context);
    }
    public GLSurfaceViewNavigable(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    @Override
    public void setup(ShapeDrawer drawer) {
        super.setup(drawer);
        this.scaleDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                camera.zoom(detector.getScaleFactor());
                return true;
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final float lastScaleFactor = camera.zoomFactor;
        scaleDetector.onTouchEvent(ev);

        final int action = ev.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            final int pointerIndex = ev.getActionIndex();
            lastTouchX = ev.getX(pointerIndex);
            lastTouchY = ev.getY(pointerIndex);
            activePointerID = ev.getPointerId(pointerIndex);

        } else if (action == MotionEvent.ACTION_MOVE) {
            if (lastScaleFactor == camera.zoomFactor) {
                final int pointerIndex = ev.getActionIndex();
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == activePointerID) {
                    final float evX = ev.getX(pointerIndex);
                    final float evY = ev.getY(pointerIndex);
                    camera.move(evX-lastTouchX, evY-lastTouchY);
                    lastTouchX = evX;
                    lastTouchY = evY;
                }
            }

        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            activePointerID = INVALID_POINTER_ID;

        } else if (action == MotionEvent.ACTION_POINTER_UP) {
            final int pointerIndex = ev.getActionIndex();
            final int pointerId = ev.getPointerId(pointerIndex);
            if (pointerId == activePointerID) {
                final int newPointerIndex = (pointerIndex == 0) ? 1 : 0;
                lastTouchX = ev.getX(newPointerIndex);
                lastTouchY = ev.getY(newPointerIndex);
                activePointerID = ev.getPointerId(newPointerIndex);
            }

        }

        requestRender();
        return true;
    }


}
