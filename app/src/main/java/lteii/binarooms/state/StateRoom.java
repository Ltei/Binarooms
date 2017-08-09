package lteii.binarooms.state;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import lteii.binarooms.R;
import lteii.binarooms.model.OLDRoom;
import lteii.binarooms.model.RoomComment;
import lteii.binarooms.standard.StdOnClickListener;
import lteii.binarooms.utils.MathUtils;
import lteii.binarooms.utils.Utils;

import static lteii.binarooms.ActMain.DATABASE;
import static lteii.binarooms.ActMain.STATES_MANAGER;
import static lteii.binarooms.ActMain.USER;


public class StateRoom extends State {


    private class CommentsDialog extends Dialog {
        private CommentsDialog(Context context) {
            super(context);
            setContentView(R.layout.layout_dialog_room_comment);
            final LinearLayout parentLinearLayout = findViewById(R.id.parent_linearlayout);
            final int nbComments = room.getNbComments();
            final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params2.setMargins(0, 0, 0, (int)MathUtils.dpToPixels(16, context));
            final int commentBackgroundColor = getResources().getColor(R.color.colorButtonLight);
            final int commentTextColor = getResources().getColor(R.color.colorTextDark);
            final int commentDateColor = getResources().getColor(R.color.colorAccent);
            for (int i=0; i<nbComments; i++) {
                final RoomComment comment = room.getComment(i);

                final LinearLayout parentLayout = new LinearLayout(context);
                parentLayout.setLayoutParams(params2);
                parentLayout.setBackgroundColor(commentBackgroundColor);
                parentLayout.setOrientation(LinearLayout.VERTICAL);

                final TextView dateTextView = new TextView(context);
                dateTextView.setText(comment.postDate.toString());
                dateTextView.setTextColor(commentDateColor);

                final TextView commentTextView = new TextView(context);
                commentTextView.setLayoutParams(params1);
                commentTextView.setGravity(Gravity.START|Gravity.CENTER);
                commentTextView.setText(comment.comment);
                commentTextView.setTextColor(commentTextColor);

                parentLayout.addView(dateTextView);
                parentLayout.addView(commentTextView);
                parentLinearLayout.addView(parentLayout);
            }
        }
    }

    private OLDRoom room = null;

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

        rootView.findViewById(R.id.parent_linear_layout).setBackgroundColor(room.getBackgroundColor());
        final boolean isBackgroundLight = MathUtils.isBrightColor(room.getBackgroundColor());

        setupTexts(isBackgroundLight, (TextView)rootView.findViewById(R.id.title_textview), (TextView)rootView.findViewById(R.id.description_textview));
        setupMedia((FrameLayout)rootView.findViewById(R.id.media_holder), context);

        setupSourceButton(isBackgroundLight, (ImageButton)rootView.findViewById(R.id.source_button), context);
        setupEditButton(isBackgroundLight, (ImageButton)rootView.findViewById(R.id.edit_button), context);
        setupCommentButton(isBackgroundLight, (ImageButton)rootView.findViewById(R.id.comment_button), context);
        setupSaveButton(isBackgroundLight, (ImageButton)rootView.findViewById(R.id.save_button), context);

        final Button[] pathButtons = new Button[] {rootView.findViewById(R.id.left_path_button), rootView.findViewById(R.id.right_path_button)};
        setupPathButtons(isBackgroundLight, pathButtons, context);

        return rootView;
    }


    private void setupTexts(boolean isBackgroundLight, TextView title, TextView description) {
        final int textColor = isBackgroundLight ? getResources().getColor(R.color.colorTextDark) : getResources().getColor(R.color.colorTextLight);
        title.setText(room.getTitle());
        title.setTextColor(textColor);
        description.setText(room.getDescription());
        description.setTextColor(textColor);
    }
    private void setupMedia(FrameLayout mediaHolder, Context context) {
        if (room.hasMedia()) {
            mediaHolder.addView(room.getMedia().getView(context));
        }
    }

    private void setupSourceButton(boolean isBackgroundLight, ImageButton sourceButton, Context context) {
        if (isBackgroundLight) sourceButton.setBackground(getResources().getDrawable(R.drawable.ic_home_black_36dp));
        sourceButton.setOnClickListener(new StdOnClickListener(context) {
            @Override
            public void onClick() {
                STATES_MANAGER.setState(new StateRoom().setup(DATABASE.sourceRoom));
            }
        });
    }
    private void setupEditButton(boolean isBackgroundLight, ImageButton editButton, Context context) {
        if (isBackgroundLight) editButton.setBackground(getResources().getDrawable(R.drawable.ic_mode_edit_black_24dp));
        editButton.setOnClickListener(new StdOnClickListener(context) {
            @Override
            public void onClick() {

            }
        });
    }
    private void setupCommentButton(boolean isBackgroundLight, ImageButton commentButton, final Context context) {
        if (isBackgroundLight) commentButton.setBackground(getResources().getDrawable(R.drawable.ic_comment_black_24dp));
        commentButton.setOnClickListener(new StdOnClickListener(context) {
            @Override
            public void onClick() {
                new CommentsDialog(context).show();
            }
        });
    }
    private void setupSaveButton(boolean isBackgroundLight, final ImageButton saveButton, final Context context) {
        final Drawable drawableOn, drawableOff;
        if (isBackgroundLight) {
            drawableOn = getResources().getDrawable(R.drawable.ic_favorite_black_24dp);
            drawableOff = getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp);
        } else {
            drawableOn = getResources().getDrawable(R.drawable.ic_favorite_white_24dp);
            drawableOff = getResources().getDrawable(R.drawable.ic_favorite_border_white_24dp);
        }

        if (USER.isSavedRoom(room)) {
            saveButton.setBackground(drawableOn);
        } else {
            saveButton.setBackground(drawableOff);
        }

        saveButton.setOnClickListener(new StdOnClickListener(context) {
            @Override
            public void onClick() {
                if (USER.isSavedRoom(room)) {
                    USER.unsaveRoom(room);
                    saveButton.setBackground(drawableOff);
                    Toast.makeText(context, "Removed from your favorites", Toast.LENGTH_SHORT).show();
                } else {
                    USER.saveRoom(room);
                    saveButton.setBackground(drawableOn);
                    Toast.makeText(context, "Added to your favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupPathButtons(boolean isBackgroundLight, Button[] pathButtons, final Context context) {
        final int buttonColor;
        final int buttonTextColor;
        if (isBackgroundLight) {
            buttonColor = getResources().getColor(R.color.colorButtonDark);
            buttonTextColor = getResources().getColor(R.color.colorTextLight);
        } else {
            buttonColor = getResources().getColor(R.color.colorButtonLight);
            buttonTextColor = getResources().getColor(R.color.colorTextDark);
        }

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
                        // Show create room dialog
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
    }


}
