package lteii._10rooms;

import android.content.Context;

import lteii._10rooms.model.room.Room;
import lteii._10rooms.utils.MathUtils;

public class Database {

    public static Room SOURCE_ROOM = null;

    public static void setup(Context context) {

        SOURCE_ROOM = new Room("Hello", "This is the source room", null, context.getResources().getColor(R.color.colorBackground));

        final Room s1 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s11 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s12 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s111 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s112 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s121 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s122 = new Room("hi", "room", null, MathUtils.randomColor());

        final Room s2 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s21 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s22 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s211 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s212 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s221 = new Room("hi", "room", null, MathUtils.randomColor());
        final Room s222 = new Room("hi", "room", null, MathUtils.randomColor());

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
