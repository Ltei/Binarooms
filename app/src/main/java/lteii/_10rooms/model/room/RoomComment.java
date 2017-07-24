package lteii._10rooms.model.room;


import lteii._10rooms.model.Date;

public class RoomComment {


    public final Date postDate;
    public final String comment;

    public RoomComment(String comment) {
        this.postDate = Date.now();
        this.comment = comment;
    }




}
