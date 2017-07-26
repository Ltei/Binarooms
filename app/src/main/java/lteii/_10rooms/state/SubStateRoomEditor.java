package lteii._10rooms.state;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import lteii._10rooms.R;
import lteii._10rooms.model.OLDRoom;
import lteii._10rooms.model.RoomMedia;
import lteii._10rooms.model.RoomMediaImage;
import lteii._10rooms.model.RoomMediaVideo;
import lteii._10rooms.utils.MathUtils;
import lteii._10rooms.utils.Utils;
import lteii._10rooms.utils.colorPicker.ColorPicker;

import static android.app.Activity.RESULT_OK;
import static lteii._10rooms.ActMain.STATES_MANAGER;

public class SubStateRoomEditor extends SubState {

    private static final int RC_SELECT_PHOTO = 0;
    private static final int RC_SELECT_VIDEO = 1;


    private Context context = null;

    private OLDRoom parent = null;
    private Integer indexInParent = null;

    private FrameLayout roomMediaFrameLayout = null;
    private LinearLayout addMediaButtonsLinearLayout = null;
    private Button deleteMediaButton = null;

    private @Nullable View roomMediaView = null;
    private @Nullable RoomMedia roomMedia = null;

    public SubStateRoomEditor() {}
    public SubStateRoomEditor setup(OLDRoom parent, int indexInParent) {
        this.parent = parent;
        this.indexInParent = indexInParent;
        return this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (parent == null) throw new IllegalStateException();
        if (indexInParent == null) throw new IllegalStateException();

        context = inflater.getContext();
        final View rootView = inflater.inflate(R.layout.state_room_editor, container, false);

        final ColorPicker bakcgroundColorPicker = rootView.findViewById(R.id.background_colorpicker);
        final TextView titleTextView = rootView.findViewById(R.id.title_textview);
        final LinearLayout parentLinearLayout = rootView.findViewById(R.id.parent_linear_layout);
        final EditText titleEditText = rootView.findViewById(R.id.title_edittext);
        final EditText descriptionEditText = rootView.findViewById(R.id.description_edittext);
        final Button addImageButton = rootView.findViewById(R.id.add_image_button);
        final Button addMusicButton = rootView.findViewById(R.id.add_music_button);
        final Button addVideoButton = rootView.findViewById(R.id.add_video_button);
        final Button confirmButton = rootView.findViewById(R.id.confirm_button);

        roomMediaFrameLayout = rootView.findViewById(R.id.room_media_framelayout);
        addMediaButtonsLinearLayout = rootView.findViewById(R.id.add_media_buttons_linearlayout);
        deleteMediaButton = rootView.findViewById(R.id.delete_media_button);

        // Setup background color picker
        bakcgroundColorPicker.setup(
                "Background color",
                getResources().getColor(R.color.colorBackground),
                new ColorPicker.OnColorChangedListener() {
                    @Override
                    public void onColorChanged(int newColor) {
                        parentLinearLayout.setBackgroundColor(newColor);
                        if (MathUtils.isBrightColor(newColor)) titleTextView.setTextColor(getResources().getColor(R.color.colorTextDark));
                        else titleTextView.setTextColor(getResources().getColor(R.color.colorTextLight));

                    }
                });


        // Setup add image button
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, RC_SELECT_PHOTO);
            }
        });
        // Setup add music button
        addMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        // Setup add video button
        addVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                startActivityForResult(intent, RC_SELECT_VIDEO);
            }
        });

        // Setup delete media button
        deleteMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRoomMedia();
            }
        });
        deleteMediaButton.setVisibility(View.GONE);

        // Setup confirm Button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String title = titleEditText.getText().toString();
                if (title.equals("")) {
                    Utils.showWarning(context, "You have to set a title");
                    return;
                }
                final String description = descriptionEditText.getText().toString();
                if (description.equals("")) {
                    Utils.showWarning(context, "You have to write something!");
                    return;
                }

                final OLDRoom created = new OLDRoom(title, description, null, bakcgroundColorPicker.getPickedColor());
                parent.setChild(indexInParent, created);

                final StateRoom state = new StateRoom();
                state.setup(created);
                STATES_MANAGER.setState(state);
            }
        });

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SELECT_PHOTO) onSelectPhotoResult(resultCode, data);
        else if (requestCode == RC_SELECT_VIDEO) onSelectVideoResult(resultCode, data);
    }
    private void onSelectPhotoResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            if (roomMedia != null) throw new IllegalStateException();
            if (roomMediaView != null) throw new IllegalStateException();

            final Uri pickedImage = data.getData();
            final String[] filePath = { MediaStore.Images.Media.DATA };
            final Cursor cursor = getActivity().getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            final String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            final Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

            cursor.close();

            addRoomMedia(new RoomMediaImage(bitmap));
        }
    }
    private void onSelectVideoResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            final Uri videoUri = data.getData();
            addRoomMedia(new RoomMediaVideo(videoUri));
        }
    }


    private void addRoomMedia(RoomMedia media) {
        if (roomMedia != null) throw new IllegalStateException();
        if (roomMediaView != null) throw new IllegalStateException();
        roomMedia = media;
        roomMediaView = roomMedia.getView(context);
        roomMediaFrameLayout.addView(roomMediaView);
        addMediaButtonsLinearLayout.setVisibility(View.GONE);
        deleteMediaButton.setVisibility(View.VISIBLE);
    }
    private void deleteRoomMedia() {
        if (roomMedia == null) throw new IllegalStateException();
        if (roomMediaView == null) throw new IllegalStateException();
        roomMediaFrameLayout.removeView(roomMediaView);
        roomMedia = null;
        roomMediaView = null;
        addMediaButtonsLinearLayout.setVisibility(View.VISIBLE);
        deleteMediaButton.setVisibility(View.GONE);
    }

}
