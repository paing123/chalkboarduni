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

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;
import edu.chalkboarduni.uniregistrationsystem.service.DepartmentService;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;

@Controller
@RequestMapping("admin")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private FacultyService facultyService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Student student, Model model) {
        model.addAttribute("model", student);
    }
	
	@RequestMapping(value = {"students"})
	public String getStudentList(Student student) {
		List<Department> departmentList = departmentService.findDepartment(new Department());
		List<UserDto> userList = studentService.getStudentUserList();
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		student.setDepartmentList(departmentList);
		student.setStudentUserList(userList);
		student.setFacultyList(facultyList);
		return "admin/students";
	}
	
	@RequestMapping(value = {"searchstudents"})
	public String searchStudentList(Student student) {
		List<Student> studentList = studentService.findStudent(student);
		List<Department> departmentList = departmentService.findDepartment(new Department());
		List<UserDto> userList = studentService.getStudentUserList();
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		student.setStudentList(studentList);
		student.setDepartmentList(departmentList);
		student.setStudentUserList(userList);
		student.setFacultyList(facultyList);
		return "admin/students";
	}
	
	@GetMapping(value = {"registerstudent"})
	public String getStudentForm(Student student,Model model) {
		student = new Student();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		List<UserDto> userList = studentService.getStudentUserList();
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		student.setDepartmentList(departmentList);
		student.setStudentUserList(userList);
		student.setFacultyList(facultyList);
		model.addAttribute("model",student);
		return "admin/registerstudent";
	}
	
	@PostMapping(value = {"registerstudent"})
	public String registerStudent(@Valid @ModelAttribute("model") Student student,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			List<UserDto> userList = studentService.getStudentUserList();
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			student.setDepartmentList(departmentList);
			student.setStudentUserList(userList);
			student.setFacultyList(facultyList);
			return "admin/registerstudent";
		}
		
		try {
			studentService.save(student);
			return "redirect:/admin/searchstudents?successMessage=registered";
		} catch (Exception e) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			List<UserDto> userList = studentService.getStudentUserList();
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			student.setDepartmentList(departmentList);
			student.setStudentUserList(userList);
			student.setFacultyList(facultyList);
			student.setErrorMessage("Duplication Error! Please check Student Id.");
			return "admin/registerstudent";
		}
	}
	
	@GetMapping("updatestudent/{id}")
	public String getExistStudent(@PathVariable("id") Integer studentId,Model model) {
		Student existStudent = new Student();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		List<UserDto> userList = studentService.getStudentUserList();
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		existStudent.setStudentId(studentId);
		existStudent = studentService.findStudent(existStudent).get(0);
		existStudent.setDepartmentList(departmentList);
		existStudent.setStudentUserList(userList);
		existStudent.setFacultyList(facultyList);
		model.addAttribute("model",existStudent);
		return "admin/updatestudent";
	}
	
	@PostMapping("updatestudent")
	public String updateStudent(@Valid @ModelAttribute("model") Student student,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			List<UserDto> userList = studentService.getStudentUserList();
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			student.setDepartmentList(departmentList);
			student.setStudentUserList(userList);
			student.setFacultyList(facultyList);
			return "admin/updatestudent";
		}
		
		studentService.update(student);
		return "redirect:/admin/searchstudents?successMessage=updated";
	}
	
	@GetMapping("deletestudent/{id}")
	public String deleteStudent(@PathVariable("id") Integer studentId) {
		studentService.delete(studentId);
		return "redirect:/admin/searchstudents?successMessage=deleted";
	}
}
