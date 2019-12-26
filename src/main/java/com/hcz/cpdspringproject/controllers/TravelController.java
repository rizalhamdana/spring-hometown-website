package com.hcz.cpdspringproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcz.cpdspringproject.pojo.Comment;
import com.hcz.cpdspringproject.pojo.Travel;
import com.hcz.cpdspringproject.service.CommentService;
import com.hcz.cpdspringproject.service.TravelService;

/**
 * NewsController
 */
@Controller
public class TravelController {

    @Autowired
    private TravelService travelService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/travels")
    public String index(Model model) {
        List<Travel> allTravels = travelService.getAllTravels();
        model.addAttribute("allTravels", allTravels);
        return "travel";
    }

    @RequestMapping("/travels/details")
    public String travelDetail(@RequestParam("travel_id") int travelId, Model model) {
        Travel travel = travelService.getTravelById(travelId);
        List<Comment> comments = commentService.getCommentsOnTravel(travelId);
        if (travel != null) {
            model.addAttribute("travel", travel);
            if (comments.size() > 0) {
                model.addAttribute("comments", comments);
            }
            return "travelDetail";
        } else {
            return "error_404";
        }
    }

    @RequestMapping("/admin/add-travel-form")
    public String showAddTravelForm(Model model) {
        Travel travel = new Travel();
        model.addAttribute("travel", travel);
        return "admin/forms/travelForm";
    }

    @RequestMapping("/admin/edit-travel-form")
    public String showEditTravelForm(Model model, @RequestParam("travel_id") int travelId) {
        Travel editTravel = travelService.getTravelById(travelId);
        model.addAttribute("editTravel", editTravel);
        return "admin/forms/travelForm";
    }

    @RequestMapping("/admin/all-travels")
    public String adminGetAllTravels(Model model) {
        List<Travel> allTravels = travelService.getAllTravels();
        if (allTravels != null) {
            model.addAttribute("allTravels", allTravels);
            return "admin/travelDashboard";
        } else {
            return "error_404";
        }
    }

    @RequestMapping(value = "/admin/travel", method = RequestMethod.GET)
    public String adminDetailTravel(@RequestParam("news_id") int travelId, Model model) {
        Travel travel = travelService.getTravelById(travelId);
        if (travel != null) {
            model.addAttribute("news", travel);
            return "admin_update_news_form";
        } else {
            return "error_404";
        }
    }

    @RequestMapping(value = "/admin/travels/update", method = RequestMethod.POST)
    public String adminUpdateTravel(@ModelAttribute("travel") Travel travel) {
        int updateTravel = travelService.updateTravel(travel);
        if (updateTravel > 0) {
            return "redirect:/admin/all-travels";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/travels", method = RequestMethod.POST)
    public String adminInsertTravel(@ModelAttribute("travel") Travel travel) {
        int insertTravel = travelService.addNewTravel(travel);
        if (insertTravel > 0) {
            return "redirect:admin/all-travels";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/travels/delete", method = RequestMethod.GET)
    public String adminDeleteTravel(@RequestParam("travel_id") int travelId) {
        int deletedTravel = travelService.deleteTravel(travelId);
        if (deletedTravel > 0) {
            return "redirect:/admin/all-travels";
        } else {
            return "error_400";
        }
    }

}
