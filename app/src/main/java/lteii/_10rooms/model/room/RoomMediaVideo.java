package lteii._10rooms.model.room;


import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class RoomMediaVideo implements RoomMedia {

    private final Uri videoURI;

    public RoomMediaVideo(Uri videoURI) {
        this.videoURI = videoURI;
    }


    @Override
    public View getView(Context context) {
        final VideoView view = new VideoView(context);
        final MediaController controller = new MediaController(context);
        view.setVideoURI(videoURI);
        view.setMediaController(controller);
        return view;
    }
}
