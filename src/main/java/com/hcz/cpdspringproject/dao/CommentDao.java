package com.hcz.cpdspringproject.dao;

import java.util.List;

import com.hcz.cpdspringproject.mapper.CommentMapper;
import com.hcz.cpdspringproject.pojo.Comment;

import com.hcz.cpdspringproject.utils.GeneralUtils;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * CommentDao
 */
public class CommentDao {
    private static JdbcTemplate template = (JdbcTemplate) GeneralUtils.getContext().getBean("jdbcTemplate");

    public int addNewComment(Comment comment) {
        String sql = "insert into comment values(null, ?,?,?)";
        int isSuccess = template.update(sql, comment.getComment(), comment.getTravel().getTravelId(),
                comment.getUser().getUserId());
        return isSuccess;
    }

    public List<Comment> getTravelComments(int travelId) {
        List<Comment> comment = null;
        String sql = "select * from comment join user on user.user_id = comment.user_id where travel_id = ?";
        comment = template.query(sql, new CommentMapper(), travelId);
        return comment;
    }

    public int deleteCommentById(int commentId) {
        String sql = "delete from comment where comment_id = ?";
        return template.update(sql, commentId);
    }
}
