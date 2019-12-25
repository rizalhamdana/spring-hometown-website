package com.hcz.cpdspringproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcz.cpdspringproject.pojo.News;
import com.hcz.cpdspringproject.pojo.Travel;
import com.hcz.cpdspringproject.service.TravelService;

/**
 * NewsController
 */
@Controller
public class TravelController {

    @Autowired
    private TravelService travelService;

    @RequestMapping("/travels")
    public String index(Model model) {
        List<Travel> allTravels = travelService.getAllTravels();
        model.addAttribute("allTravels", allTravels);
        return "travel";
    }
    
    @RequestMapping("/travels/details")
    public String travelDetail(@RequestParam("travel_id") int travelId, Model model) {
    	List<Travel> travel = (List<Travel>) travelService.getTravelById(travelId);
        if (travel != null) {
            model.addAttribute("travels", travel);
            return "travel_detail";
        } else {
            return "error_404";
        }
    }

    @RequestMapping("/admin/postsDashboard/add-travels")
    public String showAddTravelForm(Model model) {
        Travel travel = new Travel();
        model.addAttribute("travel", travel);
        return "admin/forms/travelForm";
    }

    @RequestMapping(value = "/admin/postsDashboard/travels", method = RequestMethod.PUT)
    public String adminUpdateTravel(@ModelAttribute("travel") Travel travel) {
        int updateTravel = travelService.updateTravel(travel);
        if (updateTravel > 0) {
            return "redirect:/admin/postsDashboard";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/postsDashboard/travels", method = RequestMethod.POST)
    public String adminInsertTravel(@ModelAttribute("travel") Travel travel) {
        int insertTravel = travelService.addNewTravel(travel);
        if (insertTravel > 0) {
            return "redirect:admin/postsDashboard";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/postsDashboard/travels", method = RequestMethod.DELETE)
    public String adminDeleteTravel(@RequestParam("travel_id") int travelId) {
        int deletedTravel = travelService.deleteTravel(travelId);
        if (deletedTravel > 0) {
            return "redirect:admin/postsDashboard";
        } else {
            return "error_400";
        }
    }

}
