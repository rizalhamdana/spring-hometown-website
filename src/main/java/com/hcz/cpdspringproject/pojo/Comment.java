package com.hcz.cpdspringproject.pojo;

/**
 * Comment
 */

public class Comment {

    private int commentId;
    private String comment;
    private Travel travel;
    private User user;

    public Comment() {

    }

    public Comment(int commentId, String comment, Travel travel, User user) {
        this.commentId = commentId;
        this.comment = comment;
        this.travel = travel;
        this.user = user;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}