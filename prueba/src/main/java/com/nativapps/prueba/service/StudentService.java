package com.nativapps.prueba.service;

import java.util.List;

import com.nativapps.prueba.model.Student;

/**
 * 
 * @author erick
 *
 */
public interface StudentService {
	
	public Student findByNi(String ni);
	public List<Student>findAll();
	public void saveStudent(Student student);
	public void delte(Student student);
}
