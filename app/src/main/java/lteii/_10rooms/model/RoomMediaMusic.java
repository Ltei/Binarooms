package lteii._10rooms.model;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

public class RoomMediaMusic implements RoomMedia {



    public RoomMediaMusic() {

    }


    @Override
    public View getView(Context context) {
        final ImageView view = new ImageView(context);
        return view;
    }

}
