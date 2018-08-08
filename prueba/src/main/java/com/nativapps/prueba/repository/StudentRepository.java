package com.nativapps.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nativapps.prueba.model.Student;
/**
 * 
 * @author erick
 *
 */
@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student,Integer> {
	Student findByNi(String Ni);
}
