package lteii.binarooms.model;


import android.support.annotation.Nullable;

import java.util.ArrayList;

@Deprecated
public class OLDRoom {

    public static final int NB_CHILDS = 2;


    private final Date creationDate;
    private Date lastEditDate;
    private final String title;
    private final String description;
    private final RoomMedia media;
    private final int backgroundColor;
    private final ArrayList<RoomComment> comments;
    private final OLDRoom[] childs;

    public OLDRoom(String title, String description, @Nullable RoomMedia media, int backgroundColor) {
        this.creationDate = Date.now();
        this.lastEditDate = Date.now();
        this.title = title;
        this.description = description;
        this.media = media;
        this.backgroundColor = backgroundColor;
        this.comments = new ArrayList<>();
        this.childs = new OLDRoom[NB_CHILDS];
    }


    public void setChild(int index, OLDRoom child) {
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
    public boolean hasMedia() { return media != null; }
    public RoomMedia getMedia() {
        return media;
    }

    public RoomComment getComment(int index) {
        if (index < 0 || index >= comments.size()) throw new IllegalStateException();
        return comments.get(index);
    }
    public int getNbComments() { return comments.size(); }
    public OLDRoom getChild(int index) {
        if (index < 0 || index >= NB_CHILDS) throw new IllegalStateException();
        return childs[index];
    }

    @Override
    public String toString() { return title; }


}
