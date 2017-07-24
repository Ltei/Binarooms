package lteii._10rooms.state;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lteii._10rooms.R;
import lteii._10rooms.ui.AllRoomsTreeView;

public class StateAllRooms extends State {


    public StateAllRooms() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.state_all_rooms, container, false);

        ((AllRoomsTreeView)rootView.findViewById(R.id.all_rooms_treeview)).setup();

        return rootView;
    }

}
