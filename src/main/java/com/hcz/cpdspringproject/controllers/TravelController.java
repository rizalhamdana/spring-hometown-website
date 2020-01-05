package com.hcz.cpdspringproject.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hcz.cpdspringproject.pojo.Category;
import com.hcz.cpdspringproject.pojo.Comment;
import com.hcz.cpdspringproject.pojo.Travel;
import com.hcz.cpdspringproject.pojo.User;
import com.hcz.cpdspringproject.service.CategoryService;
import com.hcz.cpdspringproject.service.CommentService;
import com.hcz.cpdspringproject.service.TravelService;
import com.hcz.cpdspringproject.utils.GeneralUtils;

/**
 * NewsController
 */
@Controller
public class TravelController {

    @Value("${file.path}")
    String uploadPath;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TravelService travelService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/travels")
    public String index(final Model model) {
        final List<Travel> allTravels = travelService.getAllTravels();
        model.addAttribute("allTravels", allTravels);
        model.addAttribute("categoryName", "All Travels");
        return "travel";
    }

    @RequestMapping("/travels/{category}")
    public String travelGroupByCategory(@PathVariable("category") int categoryId, Model model) {
        final List<Travel> travelGrouped = travelService.getTravelGrouped(categoryId);
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("allTravels", travelGrouped);
        model.addAttribute("categoryName", category.getName());
        return "travel";
    }

    @RequestMapping("/travels/details")
    public String travelDetail(@RequestParam("travel_id") final int travelId, final Model model) {
        final Travel travel = travelService.getTravelById(travelId);
        final Comment newComment = new Comment();
        final List<Comment> comments = commentService.getCommentsOnTravel(travelId);
        if (travel != null) {
            model.addAttribute("newComment", newComment);
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
    public String showAddTravelForm(final Model model) {
        final Travel travel = new Travel();
        final List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("travel", travel);
        return "admin/forms/travelForm";
    }

    @RequestMapping("/admin/edit-travel-form")
    public String showEditTravelForm(final Model model, @RequestParam("travel_id") final int travelId) {
        final List<Category> allCategories = categoryService.getAllCategories();
        final Travel editTravel = travelService.getTravelById(travelId);
        model.addAttribute("editTravel", editTravel);
        model.addAttribute("allCategories", allCategories);
        return "admin/forms/travelFormEdit";
    }

    @RequestMapping("/admin/all-travels")
    public String adminGetAllTravels(final Model model) {
        final List<Travel> allTravels = travelService.getAllTravels();
        if (allTravels != null) {
            model.addAttribute("allTravels", allTravels);
            return "admin/travelDashboard";
        } else {
            return "error_404";
        }
    }

    @RequestMapping(value = "/admin/travel", method = RequestMethod.GET)
    public String adminDetailTravel(@RequestParam("news_id") final int travelId, final Model model) {
        final Travel travel = travelService.getTravelById(travelId);
        if (travel != null) {
            model.addAttribute("news", travel);
            return "admin_update_news_form";
        } else {
            return "error_404";
        }
    }

    @RequestMapping(value = "/admin/travels/update", method = RequestMethod.POST)
    public String adminUpdateTravel(@ModelAttribute("travel") final Travel travel,
            @RequestParam("categoryId") final int categoryId, @RequestParam("file") MultipartFile file) {

        final Category category = categoryService.getCategoryById(categoryId);
        travel.setCategory(category);
        String thumbnailUpload = GeneralUtils.handleFileUpload(file, uploadPath);
        if (thumbnailUpload != null) {
            travel.setThumbnail(thumbnailUpload);
        }

        final int updateTravel = travelService.updateTravel(travel);
        if (updateTravel > 0) {
            return "redirect:/admin/all-travels";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/travel", method = RequestMethod.POST)
    public String adminInsertTravel(@ModelAttribute("travel") final Travel travel, final HttpSession session,
            final HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        final int categoryId = Integer.parseInt(request.getParameter("categoryId").toString());
        final Category category = categoryService.getCategoryById(categoryId);
        final User user = (User) session.getAttribute("authUser");
        String thumbnail = GeneralUtils.handleFileUpload(file, uploadPath);
        travel.setUser(user.getUserId());
        travel.setCategory(category);
        travel.setThumbnail(thumbnail);
        final int insertTravel = travelService.addNewTravel(travel);
        if (insertTravel > 0) {
            return "redirect:/admin/all-travels";
        } else {
            return "error_400";
        }
    }

    @RequestMapping(value = "/admin/travels/delete", method = RequestMethod.GET)
    public String adminDeleteTravel(@RequestParam("travel_id") final int travelId) {
        final int deletedTravel = travelService.deleteTravel(travelId);
        if (deletedTravel > 0) {
            return "redirect:/admin/all-travels";
        } else {
            return "error_400";
        }
    }

}
