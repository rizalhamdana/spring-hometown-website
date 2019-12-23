package com.hcz.cpdspringproject.service;

import java.util.HashMap;
import java.util.List;

import com.hcz.cpdspringproject.dao.CommentDao;
import com.hcz.cpdspringproject.dao.TravelDao;
import com.hcz.cpdspringproject.pojo.Comment;
import com.hcz.cpdspringproject.pojo.Travel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TravelService
 */
@Service
@Transactional
public class TravelService {

    public List<Travel> getAllTravels() {
        return new TravelDao().getAllTravels();
    }

    public HashMap<String, Object> getTravelById(int travelId) {
        Travel travel = new TravelDao().getTravelById(travelId);
        List<Comment> comments = new CommentDao().getTravelComments(travelId);
        HashMap<String, Object> results = new HashMap<String, Object>();
        results.put("comments", comments);
        results.put("travel", travel);
        return results;
    }

    public int addNewTravel(Travel travel) {
        return new TravelDao().addTravel(travel);
    }

    public int updateTravel(Travel travel) {
        return new TravelDao().updateTravel(travel);
    }

    public int deleteTravel(int travelId) {
        return new TravelDao().deleteTravelById(travelId);
    }

    public int likeDislikeTravel(String choice, int travelId) {
        TravelDao dao = new TravelDao();
        Travel travel = dao.getTravelById(travelId);
        int numberOfLikesOrDislikes = 0;
        if (choice.equalsIgnoreCase("likes")) {
            numberOfLikesOrDislikes = travel.getLikes();
        } else {
            numberOfLikesOrDislikes = travel.getDislikes();
        }
        return dao.likeOrDislike(choice, numberOfLikesOrDislikes, travelId);
    }
}