package com.example.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel
{
	@NotNull
	private String id_course;
    
	@NotNull
    private String name;
    
	@NotNull
    private Integer credits;
    
    private List<StudentModel> students;

}
