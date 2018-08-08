package com.nativapps.prueba.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nativapps.prueba.model.Course;
import com.nativapps.prueba.repository.CourseRepository;
import com.nativapps.prueba.service.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course findByCode(String code) {
		return courseRepository.findByCode(code);
	}

	@Override
	public void saveCourse(Course course) {
		courseRepository.save(course);
		
	}

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public void delete(String code) {
		Course course = courseRepository.findByCode(code);
		courseRepository.delete(course);		
	}

	@Override
	public List<Course> updateStudent(Course course) {
		
		courseRepository.save(course);
		
		return courseRepository.findAll();
	}

}
