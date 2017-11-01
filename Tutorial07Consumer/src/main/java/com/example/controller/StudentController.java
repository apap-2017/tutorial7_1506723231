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
import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
    		model.addAttribute("title", "Homepage");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    		model.addAttribute("title", "Tambah Mahasiswa");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa,
            Model model)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);

        model.addAttribute("title", "Tambah Mahasiswa");
        
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title", "View Mahasiswa");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title", "Mahasiswa Tidak Ditemukan");
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title", "View By NPM");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title", "Mahasiswa Tidak Ditemukan");
            return "not-found";
        }
    }
    
    @RequestMapping("/course/view/{id}")
    public String viewCourse(Model model,
    		@PathVariable(value = "id") String id) {
    	
    		CourseModel course = studentDAO.selectCourse(id);
    		
    		model.addAttribute("course", course);
    		model.addAttribute("title", "Tampilkan Course");
    		
    		return "view-course";
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("title", "View All Mahasiswa");

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    		StudentModel student = studentDAO.selectStudent(npm);
    		
        if (student != null) {
	        	studentDAO.deleteStudent (npm);
	        	model.addAttribute("title", "Delete Mahasiswa");
	        	return "delete";
        }
        else {
        		model.addAttribute("title", "Mahasiswa Tidak Ditemukan");
        		return "not-found";
        }

    }
    
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm) {
    		StudentModel student = studentDAO.selectStudent(npm);
    		
    		if (student != null) {
    			model.addAttribute("student", student);
    			model.addAttribute("title", "Update Mahasiswa");
    			return "form-update";
    		}
    		else {
    			model.addAttribute("title", "Mahasiswa Tidak Ditemukan");
    			return "not-found";
    		}
    }
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit(@Valid @ModelAttribute("student") StudentModel student, Model model) {
    	
    		studentDAO.updateStudent(student);
    		model.addAttribute("title", "Update Mahasiswa");
    		
    		return "success-update";
    }

}
