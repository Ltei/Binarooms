package lteii.binarooms.standard;


import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import lteii.binarooms.R;

public abstract class StdOnClickListener implements View.OnClickListener {

    private final Animation anim;

    public StdOnClickListener(Context context) {
        anim = AnimationUtils.loadAnimation(context, R.anim.anim_button_clicked);
    }


    @Override
    public void onClick(View view) {
        view.startAnimation(anim);
        onClick();
    }

    public abstract void onClick();

}
