package lteii.binarooms.utils;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;


public class NavigableGraphView extends NavigableView {

    private final Paint linePaint;
    private final Paint lineGradientPaint;
    private final Paint colorPaint;

    public NavigableGraphView(Context context) {
        super(context);
        linePaint = new Paint();
        lineGradientPaint = new Paint();
        colorPaint = new Paint();
    }
    public NavigableGraphView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        linePaint = new Paint();
        lineGradientPaint = new Paint();
        colorPaint = new Paint();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.translate(getWidth()/2f, getHeight()/2f);
        //canvas.translate(((View)getParent()).getWidth()/2f, ((View)getParent()).getHeight()/2f);
    }



    protected void drawCircle(Canvas canvas, float x, float y, float rayon, int color) {
        colorPaint.setColor(color);
        canvas.drawCircle((x+camera.centerX)*camera.scale, (y+camera.centerY)*camera.scale, rayon*camera.scale, colorPaint);
    }
    protected void drawLine(Canvas canvas, float x0, float y0, float x1, float y1, float width, int color) {
        linePaint.setColor(color);
        linePaint.setStrokeWidth(width*camera.scale);
        canvas.drawLine((x0+camera.centerX)*camera.scale, (y0+camera.centerY)*camera.scale,
                (x1+camera.centerX)*camera.scale, (y1+camera.centerY)*camera.scale,
                linePaint);
    }
    protected void drawLine(Canvas canvas, float x0, float y0, float x1, float y1, float width, int color0, int color1) {
        x0 = (x0+camera.centerX)*camera.scale;
        y0 = (y0+camera.centerY)*camera.scale;
        x1 = (x1+camera.centerX)*camera.scale;
        y1 = (y1+camera.centerY)*camera.scale;
        lineGradientPaint.setShader(new LinearGradient(x0,y0, x1,y1, color0, color1, Shader.TileMode.MIRROR));
        lineGradientPaint.setStrokeWidth(width*camera.scale);
        canvas.drawLine(x0, y0, x1, y1, lineGradientPaint);
    }

}
