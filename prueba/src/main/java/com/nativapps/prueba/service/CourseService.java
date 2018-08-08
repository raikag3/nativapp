package com.nativapps.prueba.service;

import java.util.List;

import com.nativapps.prueba.model.Course;
/**
 * 
 * @author erick
 *
 */


public interface CourseService  {
	
	public Course findByCode(String code);
	public void saveCourse(Course course);
	public List<Course>findAll();
	public void delete(String code);
	public List<Course> updateStudent(Course course);
}
