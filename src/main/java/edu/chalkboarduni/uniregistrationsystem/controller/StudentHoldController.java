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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.model.StudentHold;
import edu.chalkboarduni.uniregistrationsystem.model.Hold;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.service.StudentHoldService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;
import edu.chalkboarduni.uniregistrationsystem.service.HoldService;

@Controller
@RequestMapping("admin")
public class StudentHoldController {

	@Autowired
	private StudentHoldService studentHoldService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private HoldService holdService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(StudentHold studentHold, Model model) {
        model.addAttribute("model", studentHold);
    }
	
	@RequestMapping(value = {"studentholds"})
	public String getStudentHoldList(StudentHold studentHold) {
		List<Student> studentList = studentService.findStudent(new Student());
		List<Hold> holdList = holdService.findHold(new Hold());
		studentHold.setStudentList(studentList);
		studentHold.setHoldList(holdList);
		return "admin/studentholds";
	}
	
	@RequestMapping(value = {"searchstudentholds"})
	public String searchStudentHoldList(StudentHold studentHold) {
		List<StudentHold> studentHoldList = studentHoldService.findStudentHold(studentHold);
		List<Student> studentList = studentService.findStudent(new Student());
		List<Hold> holdList = holdService.findHold(new Hold());
		studentHold.setStudentHoldList(studentHoldList);
		studentHold.setStudentList(studentList);
		studentHold.setHoldList(holdList);
		return "admin/studentholds";
	}
	
	@GetMapping(value = {"registerstudenthold"})
	public String getStudentHoldForm(StudentHold studentHold, Model model) {
		studentHold = new StudentHold();
		List<Hold> holdList = holdService.findHold(new Hold());
		List<Student> studentList = studentService.findStudent(new Student());
		studentHold.setHoldList(holdList);
		studentHold.setStudentList(studentList);
		model.addAttribute("model",studentHold);
		return "admin/registerstudenthold";
	}
	
	@PostMapping(value = {"registerstudenthold"})
	public String registerStudentHold(@Valid @ModelAttribute("model") StudentHold studentHold,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Hold> holdList = holdService.findHold(new Hold());
			List<Student> studentList = studentService.findStudent(new Student());
			studentHold.setHoldList(holdList);
			studentHold.setStudentList(studentList);
			return "admin/registerstudenthold";
		}
		
		try {
			studentHoldService.save(studentHold);
			return "redirect:/admin/searchstudentholds?successMessage=registered";
		} catch (Exception e) {
			List<Hold> holdList = holdService.findHold(new Hold());
			List<Student> studentList = studentService.findStudent(new Student());
			studentHold.setHoldList(holdList);
			studentHold.setStudentList(studentList);
			studentHold.setErrorMessage("Duplication Error! Please check Student and Hold.");
			return "admin/registerstudenthold";
		}
	}
	
	@RequestMapping("deletestudenthold")
	public String deleteStudentHold(StudentHold studentHold) {
		studentHoldService.delete(studentHold);
		return "redirect:/admin/searchstudentholds?successMessage=deleted";
	}
}
