package lteii.binarooms.state;


import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import lteii.binarooms.R;
import lteii.binarooms.User;

import static lteii.binarooms.ActMain.STATES_MANAGER;
import static lteii.binarooms.ActMain.USER;

public class StateSavedRooms extends State {

    private static class SavedRoomButton extends HorizontalScrollView {
        private SavedRoomButton(Context context, final User.SavedRoom savedRoom) {
            super(context);
            // Create button
            final Button roomButton = new Button(context);
            roomButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (savedRoom.password != null) {
                roomButton.setText("Title : "+savedRoom.room.getTitle()+"\nPassword : "+savedRoom.password);
                roomButton.setMaxLines(2);
            } else {
                roomButton.setText("Title : "+savedRoom.room.getTitle());
            }
            final int padding = context.getResources().getDimensionPixelSize(R.dimen.main_horizontal_margin);
            roomButton.setPadding(padding, 0, padding, 0);
            roomButton.setGravity(Gravity.START|Gravity.CENTER);
            roomButton.setBackgroundColor(savedRoom.room.getBackgroundColor());
            roomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    STATES_MANAGER.setState(new StateRoom().setup(savedRoom.room));
                }
            });
            // Setup
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,1);
            setLayoutParams(params);
            setFillViewport(true);
            // Add button
            addView(roomButton);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.state_saved_rooms, container, false);
        final Context context = inflater.getContext();

        final LinearLayout parentLinearLayout = rootView.findViewById(R.id.parent_linearlayout);
        for (final User.SavedRoom savedRoom : USER.savedRooms) {
            parentLinearLayout.addView(new SavedRoomButton(context, savedRoom));
        }

        return rootView;
    }

}
