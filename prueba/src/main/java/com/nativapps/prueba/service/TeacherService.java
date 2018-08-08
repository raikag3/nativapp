package com.nativapps.prueba.service;

import java.util.List;

import com.nativapps.prueba.model.Teacher;

public interface TeacherService {
	public Teacher findByNi(String ni);
	public List<Teacher> findAll();
	public void saveTeacher(Teacher teacher);
	public void deleteTeacher(String ni);
}
