package com.hcz.cpdspringproject.controllers;

import com.hcz.cpdspringproject.pojo.Comment;
import com.hcz.cpdspringproject.pojo.Travel;
import com.hcz.cpdspringproject.pojo.User;
import com.hcz.cpdspringproject.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * CommentController
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addCommentToTravel(@ModelAttribute("newComment") Comment comment,
            @RequestParam("travelId") int travelId, @RequestParam("userId") int userId) {
        Travel travel = new Travel();
        travel.setTravelId(travelId);
        User user = new User();
        user.setUserId(userId);
        comment.setTravel(travel);
        comment.setUser(user);
        commentService.addComment(comment);
        return "redirect:/travels/details?travel_id=" + travelId;
    }

}