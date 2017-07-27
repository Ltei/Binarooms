package lteii._10rooms;

import android.content.Context;

import java.util.ArrayList;

import lteii._10rooms.model.OLDRoom;
import lteii._10rooms.model.Room;
import lteii._10rooms.utils.MathUtils;

public class Database {

    public static OLDRoom SOURCE_ROOM = null;

    public static void setup(Context context) {

        SOURCE_ROOM = new OLDRoom("Hello", "This is the source room", null, context.getResources().getColor(R.color.colorBackground));
        addFloors(5, SOURCE_ROOM);
    }

    private static void addFloors(int nbFloors, OLDRoom source) {
        if (nbFloors == 0) return;
        final OLDRoom child0 = new OLDRoom("Hello", "Left room", null, MathUtils.randomColor());
        final OLDRoom child1 = new OLDRoom("Hello", "Right room", null, MathUtils.randomColor());
        source.setChild(0, child0);
        source.setChild(1, child1);
        addFloors(nbFloors-1, child0);
        addFloors(nbFloors-1, child1);
    }


}
