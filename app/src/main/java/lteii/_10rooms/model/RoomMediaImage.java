package lteii._10rooms.model;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

public class RoomMediaImage implements RoomMedia {

    private final Bitmap image;

    public RoomMediaImage(Bitmap image) {
        this.image = image;
    }


    @Override
    public View getView(Context context) {
        final ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        view.setImageBitmap(image);
        return view;
    }
}
