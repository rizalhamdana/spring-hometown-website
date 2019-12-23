package com.hcz.cpdspringproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcz.cpdspringproject.pojo.Travel;
import com.hcz.cpdspringproject.service.TravelService;

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


}
