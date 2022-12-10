package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;
import edu.chalkboarduni.uniregistrationsystem.service.DepartmentService;

@Controller
@RequestMapping("admin")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Course course, Model model) {
        model.addAttribute("model", course);
    }
	
	@RequestMapping(value = {"courses"})
	public String getCourseList(Course course) {
		List<Department> departmentList = departmentService.findDepartment(new Department());
		course.setDepartmentList(departmentList);
		return "admin/courses";
	}
	
	@RequestMapping(value = {"searchcourses"})
	public String searchCourseList(Course course) {
		List<Course> courseList = courseService.findCourse(course);
		List<Department> departmentList = departmentService.findDepartment(new Department());
		course.setCourseList(courseList);
		course.setDepartmentList(departmentList);
		return "admin/courses";
	}
	
	@GetMapping(value = {"registercourse"})
	public String getCourseForm(Course course,Model model) {
		course = new Course();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		course.setDepartmentList(departmentList);
		model.addAttribute("model",course);
		return "admin/registercourse";
	}
	
	@PostMapping(value = {"registercourse"})
	public String registerCourse(@Valid @ModelAttribute("model") Course course, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			course.setDepartmentList(departmentList);
			return "admin/registercourse";
		}
		
		try {
			courseService.save(course);
			return "redirect:/admin/searchcourses?successMessage=registered";
		} catch (Exception e) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			course.setDepartmentList(departmentList);
			course.setErrorMessage("Duplication Error! Please check CourseId.");
			return "admin/registercourse";
		}
	}
	
	@GetMapping("updatecourse/{id}")
	public String getExistCourse(@PathVariable("id") String courseId,Model model) {
		Course existCourse = new Course();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		existCourse.setCourseId(courseId);
		existCourse = courseService.findCourse(existCourse).get(0);
		existCourse.setDepartmentList(departmentList);
		model.addAttribute("model",existCourse);
		return "admin/updatecourse";
	}
	
	@PostMapping("updatecourse")
	public String updateCourse(@Valid @ModelAttribute("model") Course course, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			course.setDepartmentList(departmentList);
			return "admin/updatecourse";
		}
		
		try {
			courseService.update(course);
			return "redirect:/admin/searchcourses?successMessage=updated";
		} catch (Exception e) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			course.setDepartmentList(departmentList);
			course.setErrorMessage("Duplication Error! Please check CourseId.");
			return "admin/updatecourse";
		}
	}
	
	@GetMapping("deletecourse/{id}")
	public String deleteCourse(@PathVariable("id") String courseId) {
		courseService.delete(courseId);
		return "redirect:/admin/searchcourses?successMessage=deleted";
	}
}
