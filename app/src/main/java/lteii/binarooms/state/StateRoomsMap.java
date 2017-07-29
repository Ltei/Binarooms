package lteii.binarooms.state;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lteii.binarooms.R;
import lteii.binarooms.ui.RoomsMapView;

public class StateRoomsMap extends State {


    public StateRoomsMap() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.state_rooms_map, container, false);

        ((RoomsMapView)rootView.findViewById(R.id.all_rooms_treeview)).setup();

        return rootView;
    }

}
