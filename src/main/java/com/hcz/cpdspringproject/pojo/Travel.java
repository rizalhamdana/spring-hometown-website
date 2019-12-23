package com.hcz.cpdspringproject.pojo;

/**
 * Travel
 */

public class Travel extends Post {
    private int travelId;
    private int likes;
    private int dislikes;

    public Travel() {

    }

    public Travel(int travelId, int likes, int dislikes) {
        this.travelId = travelId;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Travel(int travelId, String title, String contents, String thumbnail, int user, int likes, int dislikes) {
        super();
        this.travelId = travelId;
        this.title = title;
        this.contents = contents;
        this.thumbnail = thumbnail;
        this.user = user;
        this.setLikes(likes);
        this.setDislikes(dislikes);

    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

}