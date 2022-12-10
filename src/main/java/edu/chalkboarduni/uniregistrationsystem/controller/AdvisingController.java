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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Advising;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;
import edu.chalkboarduni.uniregistrationsystem.service.AdvisingService;

@Controller
public class AdvisingController {

	@Autowired
	private AdvisingService advisingService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private StudentService studentService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Advising advising, Model model) {
        model.addAttribute("model", advising);
    }
	
	@GetMapping("/admin/advisings")
	public String getAdvisingListForAdmin(Advising advising) {
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Student> studentList = studentService.findStudent(new Student());
		advising.setFacultyList(facultyList);
		advising.setStudentList(studentList);
		return "admin/advisings";
	}
	
	@RequestMapping("/admin/searchadvisings")
	public String searchAdvisingList(Advising advising) {
		List<Advising> advisingList = advisingService.findAdvising(advising);
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Student> studentList = studentService.findStudent(new Student());
		advising.setAdvisingList(advisingList);
		advising.setFacultyList(facultyList);
		advising.setStudentList(studentList);
		return "admin/advisings";
	}
	
	@GetMapping(value = {"/admin/registeradvising"})
	public String getAdvisingForm(Advising advising,Model model) {
		advising = new Advising();
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Student> studentList = studentService.findStudent(new Student());
		advising.setFacultyList(facultyList);
		advising.setStudentList(studentList);
		model.addAttribute("model",advising);
		return "admin/registeradvising";
	}
	
	@PostMapping(value = {"/admin/registeradvising"})
	public String registerAdvising(@Valid @ModelAttribute("model") Advising advising, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			List<Student> studentList = studentService.findStudent(new Student());
			advising.setFacultyList(facultyList);
			advising.setStudentList(studentList);
			return "admin/registeradvising";
		}
		
		advisingService.save(advising);
		return "redirect:/admin/searchadvisings?successMessage=registered";
	}
	
	@GetMapping("/admin/updateadvising/{id}")
	public String getExistAdvising(@PathVariable("id") Integer appointmentId,Model model) {
		Advising existAdvising = new Advising();
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Student> studentList = studentService.findStudent(new Student());
		existAdvising.setAppointmentId(appointmentId);
		existAdvising = advisingService.findAdvising(existAdvising).get(0);
		existAdvising.setFacultyList(facultyList);
		existAdvising.setStudentList(studentList);
		model.addAttribute("model",existAdvising);
		return "admin/updateadvising";
	}
	
	@PostMapping("/admin/updateadvising")
	public String updateAdvising(@Valid @ModelAttribute("model") Advising advising, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			List<Student> studentList = studentService.findStudent(new Student());
			advising.setFacultyList(facultyList);
			advising.setStudentList(studentList);
			return "admin/updateadvising";
		}
		
		advisingService.update(advising);
		return "redirect:/admin/searchadvisings?successMessage=updated";
	}
	
	@GetMapping("/admin/deleteadvising/{id}")
	public String deleteAdvising(@PathVariable("id") String appointmentId, Advising advising) {
		advisingService.delete(appointmentId);
		return "redirect:/admin/searchadvisings?successMessage=deleted";
	}
	
	@GetMapping("/faculty/advisings")
	public String getAdvisingListForFaculty(Advising advising, HttpSession session) {
		
		UserDto currentUser = (UserDto) session.getAttribute("currentUser");
		advising.setFacultyId(currentUser.getUserId());
		
		List<Advising> advisingList = advisingService.findAdvising(advising);
		advising.setAdvisingList(advisingList);
		return "faculty/advisings";
	}
}
