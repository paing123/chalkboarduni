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

import edu.chalkboarduni.uniregistrationsystem.model.FacultyDepartment;
import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyDepartmentService;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;
import edu.chalkboarduni.uniregistrationsystem.service.DepartmentService;

@Controller
@RequestMapping("admin")
public class FacultyDepartmentController {

	@Autowired
	private FacultyDepartmentService facultyDepartmentService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(FacultyDepartment facultyDepartment, Model model) {
        model.addAttribute("model", facultyDepartment);
    }
	
	@RequestMapping(value = {"facultydepartments"})
	public String getFacultyDepartmentList(FacultyDepartment facultyDepartment) {
		List<Department> departmentList = departmentService.findDepartment(new Department());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		
		facultyDepartment.setDepartmentList(departmentList);
		facultyDepartment.setFacultyList(facultyList);
		return "admin/facultydepartments";
	}
	
	@RequestMapping(value = {"searchfacultydepartments"})
	public String searchFacultyDepartmentList(FacultyDepartment facultyDepartment) {
		List<FacultyDepartment> facultyDepartmentList = facultyDepartmentService.findFacultyDepartment(facultyDepartment);
		List<Department> departmentList = departmentService.findDepartment(new Department());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		
		for (FacultyDepartment ftDepartment : facultyDepartmentList) {
			ftDepartment.setDateOfAppointment(DateTimeFormatter.changeDateFormat(ftDepartment.getDateOfAppointment()));
		}
		
		facultyDepartment.setFacultyDepartmentList(facultyDepartmentList);
		facultyDepartment.setDepartmentList(departmentList);
		facultyDepartment.setFacultyList(facultyList);
		return "admin/facultydepartments";
	}
	
	@GetMapping(value = {"registerfacultydepartment"})
	public String getFacultyDepartmentForm(FacultyDepartment facultyDepartment,Model model) {
		facultyDepartment = new FacultyDepartment();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		facultyDepartment.setDepartmentList(departmentList);
		facultyDepartment.setFacultyList(facultyList);
		model.addAttribute("model",facultyDepartment);
		return "admin/registerfacultydepartment";
	}
	
	@PostMapping(value = {"registerfacultydepartment"})
	public String registerFacultyDepartment(@Valid @ModelAttribute("model") FacultyDepartment facultyDepartment,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			facultyDepartment.setDepartmentList(departmentList);
			facultyDepartment.setFacultyList(facultyList);
			return "admin/registerfacultydepartment";
		}
		
		try {
			facultyDepartmentService.save(facultyDepartment);
			return "redirect:/admin/searchfacultydepartments?successMessage=registered";
		} catch (Exception e) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			facultyDepartment.setDepartmentList(departmentList);
			facultyDepartment.setFacultyList(facultyList);
			facultyDepartment.setErrorMessage("Duplication Error! Please check Faculty and Department.");
			return "admin/registerfacultydepartment";
		}
		
	}
	
	@PostMapping("updatefacultydepartmentform")
	public String getExistFacultyDepartment(FacultyDepartment facultyDepartment, Model model) {
		List<Department> departmentList = departmentService.findDepartment(new Department());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		facultyDepartment = facultyDepartmentService.findFacultyDepartment(facultyDepartment).get(0);
		facultyDepartment.setDepartmentList(departmentList);
		facultyDepartment.setFacultyList(facultyList);
		model.addAttribute("model",facultyDepartment);
		return "admin/updatefacultydepartment";
	}
	
	@PostMapping("updatefacultydepartment")
	public String updateFacultyDepartment(@Valid @ModelAttribute("model") FacultyDepartment facultyDepartment,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			facultyDepartment.setDepartmentList(departmentList);
			facultyDepartment.setFacultyList(facultyList);
			return "admin/updatefacultydepartment";
		}
		
		facultyDepartmentService.update(facultyDepartment);
		return "redirect:/admin/searchfacultydepartments?successMessage=updated";		
	}
	
	@GetMapping("deletefacultydepartment/{facid}/{did}")
	public String deleteFacultyDepartment(@PathVariable("facid") Integer facultyId,@PathVariable("did") String departmentId) {
		facultyDepartmentService.delete(facultyId,departmentId);
		return "redirect:/admin/searchfacultydepartments?successMessage=deleted";
	}
}
