package lteii._10rooms.utils.colorPicker;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import lteii._10rooms.utils.MathUtils;

public class ColorPicker extends AppCompatButton {

    public interface OnColorChangedListener {
        void onColorChanged(int newColor);
    }


    final Context context;
    String title = "";
    int color = Color.BLACK;
    private OnColorChangedListener listener = null;

    public ColorPicker(Context context) {
        super(context);
        this.context = context;
        init();
    }
    public ColorPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    private void init() {
        final ColorPicker colorPicker = this;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new PickingDialog(colorPicker).show();
            }
        });
    }

    public void setup(String title, int color, OnColorChangedListener listener) {
        this.title = title;
        this.color = color;
        this.listener = listener;
        setText(title);
        updateColorView();
    }
    public int getPickedColor() { return color; }

    void updateColorView() {
        if (MathUtils.isBrightColor(color)) {
            setTextColor(Color.BLACK);
        } else {
            setTextColor(Color.WHITE);
        }
        //setBackgroundTintList(ColorStateList.valueOf(color));
        setBackgroundColor(color);
        listener.onColorChanged(color);
    }


}
