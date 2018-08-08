package com.nativapps.prueba.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nativapps.prueba.model.Teacher;
import com.nativapps.prueba.repository.TeacherRepository;
import com.nativapps.prueba.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher findByNi(String ni) {
		return teacherRepository.findByNi(ni);
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
	}

	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	@Override
	public void deleteTeacher(String ni) {
		Teacher teacher = teacherRepository.findByNi(ni);
		if (teacher != null) teacherRepository.delete(teacher);
		
	}

}
