package com.nativapps.prueba.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nativapps.prueba.model.Teacher;

/**
 * 
 * @author erick
 *
 */
@Repository("teacherRepository")
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
		Teacher findByNi(String Ni);
		
}
