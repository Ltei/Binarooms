package lteii.binarooms.utils.colorPicker;

import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import lteii.binarooms.R;

class PickingDialog extends Dialog {

    private class OnColorSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        private OnColorSeekBarChangeListener() {}

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            colorView.onColorChanged(getOutputColor());
        }

        @Override public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override public void onStopTrackingTouch(SeekBar seekBar) {}
    }


    private final PickingDialogColorView colorView;
    private final SeekBar redSeekBar;
    private final SeekBar greenSeekBar;
    private final SeekBar blueSeekBar;

    PickingDialog(final ColorPicker colorPicker) {
        super(colorPicker.context);

        setContentView(R.layout.dialog_color_picking);
        setTitle(colorPicker.title);

        colorView = findViewById(R.id.picked_color_view);

        final int red = Color.red(colorPicker.color);
        final int green = Color.green(colorPicker.color);
        final int blue = Color.blue(colorPicker.color);
        redSeekBar = findViewById(R.id.red_seekBar);
        greenSeekBar = findViewById(R.id.green_seekBar);
        blueSeekBar = findViewById(R.id.blue_seekBar);
        redSeekBar.setProgress((int) (100f*red/255f));
        greenSeekBar.setProgress((int) (100f*green/255f));
        blueSeekBar.setProgress((int) (100f*blue/255f));
        redSeekBar.setOnSeekBarChangeListener(new OnColorSeekBarChangeListener());
        greenSeekBar.setOnSeekBarChangeListener(new OnColorSeekBarChangeListener());
        blueSeekBar.setOnSeekBarChangeListener(new OnColorSeekBarChangeListener());

        final Button okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorPicker.color = getOutputColor();
                colorPicker.updateColorView();
                dismiss();
            }
        });

        colorView.onColorChanged(getOutputColor());
    }


    private int getOutputColor() {
        final int red = (int) (255f * (float)redSeekBar.getProgress()/(float)redSeekBar.getMax());
        final int green = (int) (255f * (float)greenSeekBar.getProgress()/(float)greenSeekBar.getMax());
        final int blue = (int) (255f * (float)blueSeekBar.getProgress()/(float)blueSeekBar.getMax());
        return Color.rgb(red, green, blue);
    }




}
