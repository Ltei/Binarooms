package lteii._10rooms.utils.colorPicker;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

class PickingDialogColorView extends View {


    private Paint paint = new Paint();

    public PickingDialogColorView(Context context) {
        super(context);
    }
    public PickingDialogColorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    public void onColorChanged(int newColor) {
        paint.setColor(newColor);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

}
