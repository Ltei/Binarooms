package lteii.binarooms.model;


public class RoomComment {


    public final Date postDate;
    public final String comment;

    public RoomComment(String comment) {
        this.postDate = Date.now();
        this.comment = comment;
    }




}
