package com.nativapps.prueba.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nativapps.prueba.model.Student;
import com.nativapps.prueba.repository.StudentRepository;
import com.nativapps.prueba.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student findByNi(String ni) {
		return studentRepository.findByNi(ni);
	}

	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
		
	}
	@Override
	public List<Student>findAll(){
		return studentRepository.findAll();
	}

	@Override
	public void delte(Student student) {
		studentRepository.delete(student);
		
	}
}
