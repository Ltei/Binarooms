package lteii.binarooms.state;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lteii.binarooms.R;

public class StateInfos extends State {



    public StateInfos() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.state_hello_world, container, false);
        return rootView;
    }


}
