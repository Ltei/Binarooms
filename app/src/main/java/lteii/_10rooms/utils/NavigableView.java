package lteii._10rooms.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class NavigableView extends View {

    private static final int INVALID_POINTER_ID = -1;


    private ScaleGestureDetector scaleDetector = null;

    private int activePointerID = INVALID_POINTER_ID;
    private float lastTouchX, lastTouchY;

    protected Camera camera = new Camera();

    public NavigableView(Context context) {
        super(context);
    }
    public NavigableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    public void setup() {
        this.scaleDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                camera.scale *= detector.getScaleFactor();
                invalidate();
                return true;
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final float lastScaleFactor = camera.scale;
        scaleDetector.onTouchEvent(ev);

        final int action = ev.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            final int pointerIndex = ev.getActionIndex();
            lastTouchX = ev.getX(pointerIndex);
            lastTouchY = ev.getY(pointerIndex);
            activePointerID = ev.getPointerId(pointerIndex);

        } else if (action == MotionEvent.ACTION_MOVE) {
            if (lastScaleFactor == camera.scale) {
                final int pointerIndex = ev.getActionIndex();
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == activePointerID) {
                    final float evX = ev.getX(pointerIndex);
                    final float evY = ev.getY(pointerIndex);
                    camera.centerX += (evX-lastTouchX)/camera.scale;
                    camera.centerY += (evY-lastTouchY)/camera.scale;
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

        invalidate();
        return true;
    }



}
