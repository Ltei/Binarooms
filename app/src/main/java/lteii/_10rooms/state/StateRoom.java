package lteii._10rooms.state;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import lteii._10rooms.R;
import lteii._10rooms.model.OLDRoom;
import lteii._10rooms.utils.MathUtils;

import static lteii._10rooms.ActMain.STATES_MANAGER;


public class StateRoom extends State {

    private OLDRoom room = null;

    public StateRoom() {}
    public StateRoom setup(OLDRoom room) {
        if (room == null) throw new IllegalArgumentException();
        this.room = room;
        return this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (room == null) throw new IllegalStateException();

        final View rootView = inflater.inflate(R.layout.state_room, container, false);

        final Context context = inflater.getContext();

        // Setup background
        rootView.findViewById(R.id.parent_linear_layout).setBackgroundColor(room.getBackgroundColor());

        // Setup texts
        ((TextView) rootView.findViewById(R.id.title_textview)).setText(room.getTitle());
        ((TextView) rootView.findViewById(R.id.description_textview)).setText(room.getDescription());

        // Get buttons colors
        final int buttonColor;
        final int buttonTextColor;
        if (MathUtils.isBrightColor(room.getBackgroundColor())) {
            buttonColor = getResources().getColor(R.color.colorButtonDark);
            buttonTextColor = getResources().getColor(R.color.colorButtonDarkText);
        } else {
            buttonColor = getResources().getColor(R.color.colorButtonLight);
            buttonTextColor = getResources().getColor(R.color.colorButtonLightText);
        }

        // Setup separators
        rootView.findViewById(R.id.separator0).setBackgroundColor(buttonTextColor);
        rootView.findViewById(R.id.separator1).setBackgroundColor(buttonTextColor);

        // Setup comments button
        final Button commentsButton = rootView.findViewById(R.id.comments_button);
        commentsButton.setBackgroundColor(buttonColor);
        commentsButton.setTextColor(buttonTextColor);

        // Setup path buttons
        final Button[] pathButtons = new Button[] {
                rootView.findViewById(R.id.left_path_button),
                rootView.findViewById(R.id.right_path_button)
        };

        for (int i=0; i<pathButtons.length; i++) {
            final Button button = pathButtons[i];

            button.setBackgroundColor(buttonColor);
            button.setTextColor(buttonTextColor);

            if (room.getChild(i) != null) {
                final OLDRoom child = room.getChild(i);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        STATES_MANAGER.setState(new StateRoom().setup(child));
                    }
                });
            } else {
                final int indexInParent = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTheme);
                        builder.setTitle("There is no room here yet.\nCreate one?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                STATES_MANAGER.setSubState(new SubStateRoomEditor().setup(room, indexInParent));
                                dialog.cancel();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                });
            }

        }

        return rootView;
    }


}
