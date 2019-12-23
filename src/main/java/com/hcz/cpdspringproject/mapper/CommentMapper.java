package com.hcz.cpdspringproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hcz.cpdspringproject.pojo.Comment;
import com.hcz.cpdspringproject.pojo.Travel;
import com.hcz.cpdspringproject.pojo.User;

import org.springframework.jdbc.core.RowMapper;

/**
 * CommentMapper
 */
public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("comment_id"));
        comment.setComment(rs.getString("comment"));
        Travel travel = new Travel();
        travel.setTravelId(rs.getInt("travel_id"));
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        comment.setTravel(travel);
        comment.setUser(user);
        return comment;
    }

}