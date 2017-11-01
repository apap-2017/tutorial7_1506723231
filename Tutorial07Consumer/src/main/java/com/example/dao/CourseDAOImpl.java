package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseDAOImpl implements CourseDAO {
	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
	@Override
	public CourseModel selectCourse(String id) {
		CourseModel course = restTemplate.getForObject(
							   "http://localhost:8080/rest/course/view/"+id,
							   CourseModel.class);
		return course;
	}

	@Override
	public List<CourseModel> selectAllCourses() {
		List<CourseModel> courses = restTemplate.getForObject(
									  "http://localhost:8080/rest/course/viewall",
									  List.class);
		
		return courses;
	}
	
	
}
