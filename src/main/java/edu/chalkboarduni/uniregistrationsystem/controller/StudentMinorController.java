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

import edu.chalkboarduni.uniregistrationsystem.model.Minor;
import edu.chalkboarduni.uniregistrationsystem.model.StudentMinor;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.service.MinorService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentMinorService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;

@Controller
@RequestMapping("admin")
public class StudentMinorController {

	@Autowired
	private StudentMinorService studentMinorService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MinorService minorService;
		
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(StudentMinor studentMinor, Model model) {
        model.addAttribute("model", studentMinor);
    }
	
	@RequestMapping(value = {"studentminors"})
	public String getStudentMinorList(StudentMinor studentMinor) {
		List<Student> studentList = studentService.findStudent(new Student());
		List<Minor> MinorList = minorService.findMinor(new Minor());
		studentMinor.setStudentList(studentList);
		studentMinor.setMinorList(MinorList);
		return "admin/studentminors";
	}
	
	@RequestMapping(value = {"searchstudentminors"})
	public String searchStudentMinorList(StudentMinor studentMinor) {
		List<StudentMinor> studentMinorList = studentMinorService.findStudentMinor(studentMinor);
		List<Student> studentList = studentService.findStudent(new Student());
		List<Minor> MinorList = minorService.findMinor(new Minor());
		
		for (StudentMinor existStudentMinor : studentMinorList) {
			existStudentMinor.setDate(DateTimeFormatter.changeDateFormat(existStudentMinor.getDate()));
		}
		
		studentMinor.setStudentMinorList(studentMinorList);
		studentMinor.setStudentList(studentList);
		studentMinor.setMinorList(MinorList);
		return "admin/studentminors";
	}
	
	@GetMapping(value = {"registerstudentminor"})
	public String getStudentMinorForm(StudentMinor studentMinor,Model model) {
		studentMinor = new StudentMinor();
		List<Student> studentList = studentService.findStudent(new Student());
		List<Minor> MinorList = minorService.findMinor(new Minor());
		studentMinor.setStudentList(studentList);
		studentMinor.setMinorList(MinorList);
		model.addAttribute("model",studentMinor);
		return "admin/registerstudentminor";
	}
	
	@PostMapping(value = {"registerstudentminor"})
	public String registerStudentMinor(@Valid @ModelAttribute("model") StudentMinor studentMinor,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<Minor> MinorList = minorService.findMinor(new Minor());
			studentMinor.setStudentList(studentList);
			studentMinor.setMinorList(MinorList);
			return "admin/registerstudentminor";
		}
		
		try {
			studentMinorService.save(studentMinor);
			return "redirect:/admin/searchstudentminors?successMessage=registered";
		} catch (Exception e) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<Minor> MinorList = minorService.findMinor(new Minor());
			studentMinor.setStudentList(studentList);
			studentMinor.setMinorList(MinorList);
			studentMinor.setErrorMessage("Duplication Error! Please check Student.");
			return "admin/registerstudentminor";
		}
		
		
	}
	
	@PostMapping("updatestudentminorform")
	public String getExistStudentMinor(StudentMinor studentMinor,Model model) {
		
		List<Student> studentList = studentService.findStudent(new Student());
		List<Minor> MinorList = minorService.findMinor(new Minor());
		studentMinor = studentMinorService.findStudentMinor(studentMinor).get(0);
		studentMinor.setStudentList(studentList);
		studentMinor.setMinorList(MinorList);
		model.addAttribute("model", studentMinor);
		return "admin/updatestudentminor";
	}
	
	@PostMapping("updatestudentminor")
	public String updateExistStudentMinor(@Valid @ModelAttribute("model") StudentMinor studentMinor,BindingResult bindingResult) {
	
		if (bindingResult.hasErrors()) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<Minor> MinorList = minorService.findMinor(new Minor());
			studentMinor.setStudentList(studentList);
			studentMinor.setMinorList(MinorList);
			return "admin/registerstudentminor";
		}
		
		studentMinorService.update(studentMinor);
		return "redirect:/admin/searchstudentminors?successMessage=updated";
		
	}

	@RequestMapping("deletestudentminor")
	public String deleteStudentMinor(StudentMinor studentMinor) {
		studentMinorService.delete(studentMinor);
		return "redirect:/admin/searchstudentminors?successMessage=deleted";
	}
}
