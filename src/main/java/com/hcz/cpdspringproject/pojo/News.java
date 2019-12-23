package com.hcz.cpdspringproject.pojo;

import java.util.Date;

/**
 * News
 */

public class News extends Post {
    private int newsId;
    private Date dateCreated;

    public News() {

    }

    public News(int newsId, String title, String contents, String thumbnail, Date dateCreated, int user) {
        super();
        this.newsId = newsId;
        this.contents = contents;
        this.title = title;
        this.thumbnail = thumbnail;
        this.dateCreated = dateCreated;
        this.user = user;

    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}