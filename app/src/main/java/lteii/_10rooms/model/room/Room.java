package lteii._10rooms.model.room;


import android.support.annotation.Nullable;

import java.util.ArrayList;

import lteii._10rooms.model.Date;

public class Room {

    public static final int NB_CHILDS = 2;


    private final Date postDate;
    private final String title;
    private final String description;
    private final RoomMedia media;
    private final int backgroundColor;
    private final ArrayList<RoomComment> comments;
    private final Room[] childs;

    public Room(String title, String description, @Nullable RoomMedia media, int backgroundColor) {
        this.postDate = Date.now();
        this.title = title;
        this.description = description;
        this.media = media;
        this.backgroundColor = backgroundColor;
        this.comments = new ArrayList<>();
        this.childs = new Room[NB_CHILDS];
    }


    public void setChild(int index, Room child) {
        if (index < 0 || index >= NB_CHILDS) throw new IllegalStateException();
        if (childs[index] != null) throw new IllegalStateException();
        childs[index] = child;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getBackgroundColor() { return backgroundColor; }
    public RoomComment getComment(int index) {
        if (index < 0 || index >= comments.size()) throw new IllegalStateException();
        return comments.get(index);
    }
    public int getNbComments() { return comments.size(); }
    public Room getChild(int index) {
        if (index < 0 || index >= NB_CHILDS) throw new IllegalStateException();
        return childs[index];
    }


}
