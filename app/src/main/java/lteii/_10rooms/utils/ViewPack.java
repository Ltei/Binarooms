package lteii._10rooms.utils;

import android.view.View;

public class ViewPack {

    private final View[] views;

    public ViewPack(View...views) {
        this.views = views;
    }


    public void setVisibility(int visibility) {
        for (View view : views) {
            view.setVisibility(visibility);
        }
    }
    public void setBackgroundColor(int color) {
        for (View view : views) {
            view.setBackgroundColor(color);
        }
    }

}
