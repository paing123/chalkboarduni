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

import edu.chalkboarduni.uniregistrationsystem.model.Major;
import edu.chalkboarduni.uniregistrationsystem.model.StudentMajor;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.service.MajorService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentMajorService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;

@Controller
@RequestMapping("admin")
public class StudentMajorController {

	@Autowired
	private StudentMajorService studentMajorService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MajorService majorService;
		
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(StudentMajor studentMajor, Model model) {
        model.addAttribute("model", studentMajor);
    }
	
	@RequestMapping(value = {"studentmajors"})
	public String getStudentMajorList(StudentMajor studentMajor) {
		List<Student> studentList = studentService.findStudent(new Student());
		List<Major> majorList = majorService.findMajor(new Major());
		studentMajor.setStudentList(studentList);
		studentMajor.setMajorList(majorList);
		return "admin/studentmajors";
	}
	
	@RequestMapping(value = {"searchstudentmajors"})
	public String searchStudentMajorList(StudentMajor studentMajor) {
		List<StudentMajor> studentMajorList = studentMajorService.findStudentMajor(studentMajor);
		List<Student> studentList = studentService.findStudent(new Student());
		List<Major> majorList = majorService.findMajor(new Major());
		
		for (StudentMajor existStudentMajor : studentMajorList) {
			existStudentMajor.setDate(DateTimeFormatter.changeDateFormat(existStudentMajor.getDate()));
		}
		
		studentMajor.setStudentMajorList(studentMajorList);
		studentMajor.setStudentList(studentList);
		studentMajor.setMajorList(majorList);
		return "admin/studentmajors";
	}
	
	@GetMapping(value = {"registerstudentmajor"})
	public String getStudentMajorForm(StudentMajor studentMajor,Model model) {
		studentMajor = new StudentMajor();
		List<Student> studentList = studentService.findStudent(new Student());
		List<Major> majorList = majorService.findMajor(new Major());
		studentMajor.setStudentList(studentList);
		studentMajor.setMajorList(majorList);
		model.addAttribute("model",studentMajor);
		return "admin/registerstudentmajor";
	}
	
	@PostMapping(value = {"registerstudentmajor"})
	public String registerStudentMajor(@Valid @ModelAttribute("model") StudentMajor studentMajor,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<Major> majorList = majorService.findMajor(new Major());
			studentMajor.setStudentList(studentList);
			studentMajor.setMajorList(majorList);
			return "admin/registerstudentmajor";
		}
		
		try {
			studentMajorService.save(studentMajor);
			return "redirect:/admin/searchstudentmajors?successMessage=registered";
		} catch (Exception e) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<Major> majorList = majorService.findMajor(new Major());
			studentMajor.setStudentList(studentList);
			studentMajor.setMajorList(majorList);
			studentMajor.setErrorMessage("Duplication Error! Please check Student.");
			return "admin/registerstudentmajor";
		}
	}
	
	@PostMapping("updatestudentmajorform")
	public String getExistStudentMajor(StudentMajor studentMajor,Model model) {
		
		List<Student> studentList = studentService.findStudent(new Student());
		List<Major> majorList = majorService.findMajor(new Major());
		studentMajor = studentMajorService.findStudentMajor(studentMajor).get(0);
		studentMajor.setStudentList(studentList);
		studentMajor.setMajorList(majorList);
		model.addAttribute("model", studentMajor);
		return "admin/updatestudentmajor";
	}
	
	@PostMapping("updatestudentmajor")
	public String updateStudentMajor(@Valid @ModelAttribute("model") StudentMajor studentMajor,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<Major> majorList = majorService.findMajor(new Major());
			studentMajor.setStudentList(studentList);
			studentMajor.setMajorList(majorList);
			return "admin/updatestudentmajor";
		}
		
		studentMajorService.update(studentMajor);
		return "redirect:/admin/searchstudentmajors?successMessage=updated";
	}

	@RequestMapping("deletestudentmajor")
	public String deleteStudentMajor(StudentMajor studentMajor) {
		studentMajorService.delete(studentMajor);
		return "redirect:/admin/searchstudentmajors?successMessage=deleted";
	}
}
