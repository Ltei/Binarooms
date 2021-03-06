package lteii.binarooms;


import android.support.annotation.Nullable;

import java.util.ArrayList;

import lteii.binarooms.model.OLDRoom;

public class User {


    public static class SavedRoom {

        public OLDRoom room;
        public @Nullable String password;

        private SavedRoom(OLDRoom room, @Nullable String password) {
            this.room = room;
            this.password = password;
        }

    }


    public ArrayList<SavedRoom> savedRooms;

    public User(Database database) {
        savedRooms = new ArrayList<>();
        saveRoom(database.sourceRoom, "hellozezghkjhksjhoqhgqlhgqfhgjkfhjqskldhfqhfdqfqsdfqqg");
    }


    public void saveRoom(OLDRoom room) {
        if (isSavedRoom(room)) throw new IllegalStateException();
        savedRooms.add(new SavedRoom(room, null));
    }
    public void saveRoom(OLDRoom room, String password) {
        if (isSavedRoom(room)) throw new IllegalStateException();
        savedRooms.add(new SavedRoom(room, password));
    }
    public void unsaveRoom(OLDRoom room) {
        if (!isSavedRoom(room)) throw new IllegalStateException();
        for (SavedRoom savedRoom : savedRooms) {
            if (room == savedRoom.room) {
                savedRooms.remove(savedRoom);
                return;
            }
        }
        throw new IllegalStateException();
    }
    public boolean isSavedRoom(OLDRoom room) {
        for (SavedRoom savedRoom : savedRooms)
            if (room == savedRoom.room)
                return true;
        return false;
    }


    public void saveUser() {

    }





}
