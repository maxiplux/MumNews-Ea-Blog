package com.mum.news.ea.blog.models;

/**
 * User: franc
 * Date: 24/09/2018
 * Time: 5:40
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {

    @Id
    private String commentId;

    private long seq;

    private String comment;
    private Date creationDate = new Date();
    private User user;

    private long articleId;


    public Comment() {
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + commentId + '\'' +
                ", comment='" + comment + '\'' +
                ", creationDate=" + creationDate +
                ", articleId=" + articleId +
                '}';
    }
}
