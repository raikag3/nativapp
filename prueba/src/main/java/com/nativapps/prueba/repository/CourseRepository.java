package com.nativapps.prueba.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nativapps.prueba.model.Course;
@Repository("courseRepository")
public interface CourseRepository  extends JpaRepository<Course,String>{
	
	Course findByCode(String code);

}
