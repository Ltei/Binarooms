package lteii.binarooms.state;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import lteii.binarooms.R;
import lteii.binarooms.model.OLDRoom;
import lteii.binarooms.standard.StdOnClickListener;
import lteii.binarooms.utils.MathUtils;
import lteii.binarooms.utils.Utils;

import static lteii.binarooms.ActMain.DATABASE;
import static lteii.binarooms.ActMain.STATES_MANAGER;
import static lteii.binarooms.ActMain.USER;


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

        // Setup media
        if (room.hasMedia()) {
            ((FrameLayout)rootView.findViewById(R.id.media_holder)).addView(room.getMedia().getView(context));
        }

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

        // Setup source button
        final ImageButton sourceButton = rootView.findViewById(R.id.source_button);
        sourceButton.setOnClickListener(new StdOnClickListener(context) {
            @Override
            public void onClick() {
                STATES_MANAGER.setState(new StateRoom().setup(DATABASE.sourceRoom));
            }
        });

        // Setup edit button
        final ImageButton editButton = rootView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(new StdOnClickListener(context) {
            @Override
            public void onClick() {

            }
        });

        // Setup comment button
        final ImageButton commentButton = rootView.findViewById(R.id.comment_button);
        commentButton.setOnClickListener(new StdOnClickListener(context) {
            @Override
            public void onClick() {

            }
        });

        // Setup save button
        final ImageButton saveButton = rootView.findViewById(R.id.save_button);
        if (USER.isSavedRoom(room)) {
            saveButton.setBackground(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));
        } else {
            saveButton.setBackground(getResources().getDrawable(R.drawable.ic_favorite_border_white_24dp));
        }
        saveButton.setOnClickListener(new StdOnClickListener(context) {
            @Override
            public void onClick() {
                if (USER.isSavedRoom(room)) {
                    USER.unsaveRoom(room);
                    saveButton.setBackground(getResources().getDrawable(R.drawable.ic_favorite_border_white_24dp));
                    Toast.makeText(context, "Removed from your favorites", Toast.LENGTH_SHORT).show();
                } else {
                    USER.saveRoom(room);
                    saveButton.setBackground(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));
                    Toast.makeText(context, "Added to your favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                                STATES_MANAGER.setState(new SubStateRoomEditor().setup(room, indexInParent));
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
