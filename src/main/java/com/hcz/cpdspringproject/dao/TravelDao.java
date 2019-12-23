package com.hcz.cpdspringproject.dao;

import java.util.List;

import com.hcz.cpdspringproject.mapper.TravelMapper;

import com.hcz.cpdspringproject.pojo.Travel;
import com.hcz.cpdspringproject.utils.GeneralUtils;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * TravelDao
 */
public class TravelDao {
    private static JdbcTemplate template = (JdbcTemplate) GeneralUtils.getContext().getBean("jdbcTemplate");

    public Travel getTravelById(final int travelId) {
        final String sql = "select * from travel join category on travel.category = category.category_id order by travel.travel_id desc where travel_id = ? ";
        return template.queryForObject(sql, new TravelMapper(), travelId);
    }

    public List<Travel> getAllTravels() {
        String sql = "select * from travel join category on travel.category = category.category_id order by travel.travel_id desc";
        return template.query(sql, new TravelMapper());
    }

    public int addTravel(Travel travel) {
        String sql = "insert into travel values(null, ?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql, travel.getTitle(), travel.getContents(), travel.getThumbnail(), travel.getUser(), 0,
                0, travel.getCategory().getCategoryId());
    }

    public int updateTravel(Travel travel) {
        String sql = "update travel set title = ?, contents = ?, thumbnail = ?, date_created = ?, user = ?, category = ? where travel_id = "
                + travel.getTravelId();
        return template.update(sql, travel.getTitle(), travel.getContents(), travel.getThumbnail(), travel.getUser(),
                travel.getCategory().getCategoryId());
    }

    public int likeOrDislike(String column, int numberOfLikesOrDislikes, int travelId) {
        String sql = "update travel set " + column + "=" + numberOfLikesOrDislikes + " where travel_id=" + travelId;
        return template.update(sql);
    }

    public int deleteTravelById(int travelId) {
        String sql = "delete from travel where travel_id = ?";
        return template.update(sql, travelId);
    }
}