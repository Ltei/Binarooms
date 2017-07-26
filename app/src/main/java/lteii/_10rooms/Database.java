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

        final OLDRoom s1 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s11 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s12 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s111 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s112 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s121 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s122 = new OLDRoom("hi", "room", null, MathUtils.randomColor());

        final OLDRoom s2 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s21 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s22 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s211 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s212 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s221 = new OLDRoom("hi", "room", null, MathUtils.randomColor());
        final OLDRoom s222 = new OLDRoom("hi", "room", null, MathUtils.randomColor());

        s1.setChild(0, s11);
        s1.setChild(1, s12);

        s11.setChild(0, s111);
        s11.setChild(1, s112);

        s12.setChild(0, s121);
        s12.setChild(1, s122);

        s2.setChild(0, s21);
        s2.setChild(1, s22);

        s21.setChild(0, s211);
        s21.setChild(1, s212);

        s22.setChild(0, s221);
        s22.setChild(1, s222);

        SOURCE_ROOM.setChild(0, s1);
        SOURCE_ROOM.setChild(1, s2);
    }


}
