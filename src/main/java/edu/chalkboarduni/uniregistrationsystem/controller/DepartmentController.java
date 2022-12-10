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

import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.service.DepartmentService;

@Controller
@RequestMapping("admin")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Department department, Model model) {
        model.addAttribute("model", department);
    }
	
	@RequestMapping(value = {"departments"})
	public String getDepartmentList(Department department) {
		return "admin/departments";
	}
	
	@RequestMapping(value = {"searchdepartments"})
	public String searchDepartmentList(Department department) {
		List<Department> departmentList = departmentService.findDepartment(department);
		department.setDepartmentList(departmentList);
		return "admin/departments";
	}
	
	@GetMapping(value = {"registerdepartment"})
	public String getDepartmentForm(Department department) {
		department = new Department();
		return "admin/registerdepartment";
	}
	
	@PostMapping(value = {"registerdepartment"})
	public String registerDepartment(@Valid @ModelAttribute("model") Department department, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "admin/registerdepartment";
		}
		
		try {
			departmentService.save(department);
			return "redirect:/admin/searchdepartments?successMessage=registered";
		} catch (Exception e) {
			department.setErrorMessage("Duplication Error! Please check DepartmentId.");
			return "admin/registerdepartment";
		}
	}
	
	@GetMapping("updatedepartment/{id}")
	public String getExistDepartment(@PathVariable("id") String departmentId,Model model) {
		Department existDepartment = new Department();
		existDepartment.setDepartmentId(departmentId);
		existDepartment = departmentService.findDepartment(existDepartment).get(0);
		model.addAttribute("model",existDepartment);
		return "admin/updatedepartment";
	}
	
	@PostMapping("updatedepartment")
	public String updateDepartment(@Valid @ModelAttribute("model") Department department, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "admin/updatedepartment";
		}
		
		departmentService.update(department);
		return "redirect:/admin/searchdepartments?successMessage=updated";
	}
	
	@GetMapping("deletedepartment/{id}")
	public String deleteDepartment(@PathVariable("id") String departmentId) {
		departmentService.delete(departmentId);
		return "redirect:/admin/searchdepartments?successMessage=deleted";
	}
}
