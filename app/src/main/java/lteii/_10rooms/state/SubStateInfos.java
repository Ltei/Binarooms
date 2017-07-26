package lteii._10rooms.state;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lteii._10rooms.R;

public class SubStateInfos extends SubState {



    public SubStateInfos() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.state_hello_world, container, false);
        return rootView;
    }


}
