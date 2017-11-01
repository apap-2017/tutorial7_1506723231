package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.service.CourseService;

@Controller
public class CourseController
{
    @Autowired
    CourseService courseDAO;
    
    @RequestMapping("/course/view/{id}")
    public String viewCourse(Model model,
    		@PathVariable(value = "id") String id) {
    	
    		CourseModel course = courseDAO.selectCourse(id);
    		
    		model.addAttribute("course", course);
    		model.addAttribute("title", "Tampilkan Course");
    		
    		return "view-course";
    }
    
    @RequestMapping("/course/viewall")
    public String viewAllCourses(Model model) {
    		List<CourseModel> courses = courseDAO.selectAllCourses();
    	
    		model.addAttribute("courses", courses);
    	
    		return "viewall-course";
    }

}
