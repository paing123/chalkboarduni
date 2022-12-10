package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Advising;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.service.AdvisingService;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentAdvisingController {
	
	@Autowired
	private AdvisingService advisingService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private FacultyService facultyService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Advising advising, Model model) {
        model.addAttribute("model", advising);
    }
	
	@RequestMapping(value = {"advisings"})
	public String searchStudentAdvising(Advising advising, HttpSession session) {
		
		if(advising.getStudentId() == null) {
			UserDto student = (UserDto) session.getAttribute("currentUser");
			advising.setStudentId(student.getUserId());
			
			Student stud = new Student();
			stud.setStudentId(student.getUserId());
			stud = studentService.findStudent(stud).get(0);
			advising.setFacultyId(stud.getFacultyId());
		}

		List<Advising> advisings = advisingService.findAdvising(advising);
		advising.setAdvisingList(advisings);
		
		return "student/advisings";
	}
	
	@PostMapping(value = {"getadvisingform"})
	public String getStudentAdvising(Advising advising) {
		
		List<Student> students = studentService.findStudent(new Student());
		List<Faculty> facultries = facultyService.findFaculty(new Faculty());
		advising.setStudentList(students);
		advising.setFacultyList(facultries);
		
		return "student/registeradvising";
	}
	
	@PostMapping(value = {"registeradvising"})
	public String registerStudentAdvising(@Valid @ModelAttribute("model") Advising advising, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			List<Student> studentList = studentService.findStudent(new Student());
			advising.setFacultyList(facultyList);
			advising.setStudentList(studentList);
			return "/student/registeradvising";
		}
		
		advisingService.save(advising);
		return "redirect:/student/advisings?successMessage=booked";
	}
}
