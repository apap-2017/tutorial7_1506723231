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
public class StudentServiceDatabase implements StudentService
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
    public List<StudentModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public void addStudent (StudentModel student)
    {
        studentMapper.addStudent (student);
        log.info("masuk");
    }


    @Override
    public void deleteStudent (String npm)
    {
    		studentMapper.deleteStudent(npm);
    		log.info("student " + npm + " deleted");
    }
    
    @Override
    public void updateStudent (StudentModel student) {
    		studentMapper.updateStudent(student);
    		log.info("student " + student.getNpm() + " updated");
    }


	@Override
	public CourseModel selectCourse(String id) {
		CourseModel course = studentMapper.selectCourse(id);
		log.info ("select course with id " + course.getIdCourse());
		return course;
	}

}
