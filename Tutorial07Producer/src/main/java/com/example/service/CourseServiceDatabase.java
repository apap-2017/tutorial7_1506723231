package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentMapper;
import com.example.model.CourseModel;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseServiceDatabase implements CourseService
{
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<CourseModel> selectAllCourses()
    {
        log.info ("select all courses");
        return studentMapper.selectAllCourses();
    }

	@Override
	public CourseModel selectCourse(String id) {
		CourseModel course = studentMapper.selectCourse(id);
		log.info ("select course with id " + course.getId_course());
		return course;
	}

}
