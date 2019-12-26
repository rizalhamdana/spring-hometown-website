package com.hcz.cpdspringproject.service;

import java.util.List;

import com.hcz.cpdspringproject.dao.CommentDao;
import com.hcz.cpdspringproject.pojo.Comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CommentService
 */
@Service
@Transactional
public class CommentService {

    public int addComment(Comment comment) {
        return new CommentDao().addNewComment(comment);
    }

    public int deleteComment(int commentId) {
        return new CommentDao().deleteCommentById(commentId);
    }

    public List<Comment> getCommentsOnTravel(int travelId) {
        return new CommentDao().getTravelComments(travelId);
    }

}