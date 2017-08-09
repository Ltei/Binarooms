package lteii.binarooms;

import android.content.Context;
import android.graphics.BitmapFactory;

import lteii.binarooms.model.OLDRoom;
import lteii.binarooms.model.RoomMedia;
import lteii.binarooms.model.RoomMediaImage;
import lteii.binarooms.utils.MathUtils;

public class Database {


    public final OLDRoom sourceRoom;

    private RoomMedia media0;
    private RoomMedia media1;

    public Database(Context context) {
        media0 = new RoomMediaImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.test_circle2));
        media1 = new RoomMediaImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.test_circle));
        sourceRoom = new OLDRoom("Hello", "This is the source room", media0, context.getResources().getColor(R.color.colorBackground));
        sourceRoom.addComment("commentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcomment");
        sourceRoom.addComment("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        sourceRoom.addComment("bbsbsvsdsdfsdfsdbnzbgbensgn e gqbqbg g g  gbz gzg qe tgrdtzbg G QB grs");
        sourceRoom.addComment("commentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcomment");
        sourceRoom.addComment("commentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcomment");
        sourceRoom.addComment("commentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcomment");
        sourceRoom.addComment("commentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcomment");
        sourceRoom.addComment("commentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcommentcomment");
        addFloors(5, sourceRoom);
    }


    private void addFloors(int nbFloors, OLDRoom source) {
        if (nbFloors == 0) return;
        final OLDRoom child0 = new OLDRoom("Hello", "Left room", media0, MathUtils.randomColor());
        final OLDRoom child1 = new OLDRoom("Hello", "Right room", media1, MathUtils.randomColor());
        source.setChild(0, child0);
        source.setChild(1, child1);
        addFloors(nbFloors-1, child0);
        addFloors(nbFloors-1, child1);
    }


}
