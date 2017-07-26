package lteii._10rooms.model;


import android.support.annotation.Nullable;

public class Room {

    public static final int NB_CHILDS = 2;

    public static Room create(Room parent, int indexInParent, String title, String description, @Nullable RoomMedia media, int backgroundColor) {
        if (indexInParent < 0 || indexInParent >= NB_CHILDS) throw new IllegalArgumentException();
        throw new IllegalStateException();
    }


    private final long id;

    public Room(long id) {
        this.id = id;
    }


    public String getTitle() {
        throw new IllegalStateException();
    }
    public String getDescription() {
        throw new IllegalStateException();
    }
    public RoomMedia getMedia() {
        throw new IllegalStateException();
    }
    public int getBackgroundColor() {
        throw new IllegalStateException();
    }

    public Date getCreationDate() {
        throw new IllegalStateException();
    }
    public Date getLastEditDate() {
        throw new IllegalStateException();
    }

    public int getNbComments() {
        throw new IllegalStateException();
    }
    public RoomComment getComment(int index) {
        if (index < 0 || index >= getNbComments()) throw new IllegalArgumentException();
        throw new IllegalStateException();
    }

    public Room getChild(int index) {
        if (index < 0 || index >= NB_CHILDS) throw new IllegalArgumentException();
        throw new IllegalStateException();
    }



}
