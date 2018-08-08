package com.nativapps.prueba.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nativapps.prueba.model.Course;
import com.nativapps.prueba.model.Student;
import com.nativapps.prueba.model.Teacher;
import com.nativapps.prueba.model.User;
import com.nativapps.prueba.service.CourseService;
import com.nativapps.prueba.service.StudentService;
import com.nativapps.prueba.service.TeacherService;
import com.nativapps.prueba.service.UserService;

/**
 * 
 * @author erick
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService ;	
	
	@Autowired 
	private CourseService courseService;
	
	@Autowired 
	private TeacherService teacherService;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	
	//Student
	@RequestMapping(value="/registrationStudent", method = RequestMethod.GET)
	public ModelAndView registrationStudent(){
		ModelAndView modelAndView = new ModelAndView();
		Student student = new Student();
		modelAndView.addObject("student", student);
		List<Student> students = studentService.findAll();
		modelAndView.addObject("students", students);
		modelAndView.setViewName("registrationStudent");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registrationStudent", method = RequestMethod.POST)
	public ModelAndView createNewStudent(@Valid Student student, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Student studentExists = studentService.findByNi(student.getNi());
		if (studentExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registrationStudent");
		} else {
			studentService.saveStudent(student);
			modelAndView.addObject("successMessage", "student has been registered successfully");
			modelAndView.addObject("student", new Student());
			List<Student> students = studentService.findAll();
			modelAndView.addObject("students", students);
			modelAndView.setViewName("registrationStudent");
			
		}
		
		return modelAndView;
	}

	@RequestMapping ("/StudentDelete")
	@ResponseBody
	public ModelAndView deleteStudent(String ni){
		ModelAndView modelAndView = new ModelAndView();
		
		Student student = studentService.findByNi(ni);
		if (student!=null)studentService.delte(student);
		
		
		student = new Student();
		modelAndView.addObject("student", student);
		List<Student> students = studentService.findAll();
		modelAndView.addObject("students", students);
		modelAndView.setViewName("registrationStudent");
		return modelAndView;
	}
	
	//course
	@RequestMapping(value="/registrationCourse", method = RequestMethod.GET)
	public ModelAndView registrationCourse(){
		ModelAndView modelAndView = new ModelAndView();
		Course course = new Course();
		List<Course> courses = courseService.findAll();		
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("course", course);
		modelAndView.setViewName("registrationCourse");
		return modelAndView;
	}
	@RequestMapping(value = "/registrationCourse", method = RequestMethod.POST)
	public ModelAndView createNewCourse(@Valid Course course, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Course courseExists = courseService.findByCode(course.getCode());
		if (courseExists != null) {
			bindingResult
					.rejectValue("code", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registrationCourse");
		} else {
			courseService.saveCourse(course);
			modelAndView.addObject("successMessage", "course has been created successfully");
			modelAndView.addObject("course", new Course());
			modelAndView.setViewName("registrationCourse");
			List<Course> courses = courseService.findAll();		
			modelAndView.addObject("courses", courses);
			
		}
		
		return modelAndView;
	}

	@RequestMapping("/courseDelete")
	@ResponseBody
	public ModelAndView deleteCourse(String code) {
					    
	    ModelAndView modelAndView = new ModelAndView();
	    Course course = courseService.findByCode(code);
		
		modelAndView.addObject("course", course);
		modelAndView.setViewName("updateCourse");
		return modelAndView;
			    
	}
	
	@RequestMapping("/courseUpdate")
	@ResponseBody
	public ModelAndView updateCourse(String code) {					    
	  Course course = courseService.findByCode(code);	    
	    ModelAndView modelAndView = new ModelAndView();		
		List<Course> courses = courseService.findAll();		
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("course", course);
		modelAndView.setViewName("updateCourse");
		return modelAndView;
			    
	}
	
	@RequestMapping(value = "/courseUpdate", method = RequestMethod.POST)
	public ModelAndView updateCourse(@Valid Course course, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Course courseExists = courseService.findByCode(course.getCode());
		if (courseExists == null) {
			bindingResult
					.rejectValue("code", "error.user",
							"we cant find this course registered");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registrationCourse");
		} else {
			courseService.saveCourse(course);
			modelAndView.addObject("successMessage", "course has been created successfully");
			modelAndView.addObject("course", new Course());
			modelAndView.setViewName("registrationCourse");
			List<Course> courses = courseService.findAll();		
			modelAndView.addObject("courses", courses);
			
		}
		
		return modelAndView;
	}
	
	//teacher
	@RequestMapping(value="/registrationTeacher", method = RequestMethod.GET)
	public ModelAndView registrationTeacher(){
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacher = new Teacher();
		List<Teacher> teachers =  teacherService.findAll();
		modelAndView.addObject("teachers", teachers);
		modelAndView.addObject("teacher", teacher);
		modelAndView.setViewName("registrationTeacher");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registrationTeacher", method = RequestMethod.POST)
	public ModelAndView createNewTeacher(@Valid Teacher teacher, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacherExists = teacherService.findByNi(teacher.getNi());
		if (teacherExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registrationTeacher");
		} else {
			teacherService.saveTeacher(teacher);
			modelAndView.addObject("successMessage", "Teacher has been added successfully");
			List<Teacher> teachers =  teacherService.findAll();
			modelAndView.addObject("teachers", teachers);
			modelAndView.addObject("teacher", new Teacher());
			modelAndView.setViewName("registrationTeacher");
			
		}
		
		return modelAndView;
	}
		
	
	@RequestMapping("/teacherDelete")
	@ResponseBody
	public ModelAndView deleteTeacher (String ni){
		Teacher teacher = teacherService.findByNi(ni) ;
		if (teacher!=null)	teacherService.deleteTeacher(ni);		
		ModelAndView modelAndView = new ModelAndView();
		teacher = new Teacher();
		List<Teacher> teachers =  teacherService.findAll();
		modelAndView.addObject("teachers", teachers);
		modelAndView.addObject("teacher", teacher);
		modelAndView.setViewName("registrationTeacher");
		return modelAndView;
	}
	
	
	
	
	
}
