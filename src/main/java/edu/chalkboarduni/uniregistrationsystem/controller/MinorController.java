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

import edu.chalkboarduni.uniregistrationsystem.model.Minor;
import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.service.MinorService;
import edu.chalkboarduni.uniregistrationsystem.service.DepartmentService;

@Controller
@RequestMapping("admin")
public class MinorController {

	@Autowired
	private MinorService minorService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Minor minor, Model model) {
        model.addAttribute("model", minor);
    }
	
	@RequestMapping(value = {"minors"})
	public String getMinorList(Minor minor) {
		List<Department> departmentList = departmentService.findDepartment(new Department());
		minor.setDepartmentList(departmentList);
		return "admin/minors";
	}
	
	@RequestMapping(value = {"searchminors"})
	public String searchMinorList(Minor minor) {
		List<Minor> minorList = minorService.findMinor(minor);
		List<Department> departmentList = departmentService.findDepartment(new Department());
		minor.setMinorList(minorList);
		minor.setDepartmentList(departmentList);
		return "admin/minors";
	}
	
	@GetMapping(value = {"registerminor"})
	public String getMinorForm(Minor minor,Model model) {
		minor = new Minor();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		minor.setDepartmentList(departmentList);
		model.addAttribute("model",minor);
		return "admin/registerminor";
	}
	
	@PostMapping(value = {"registerminor"})
	public String registerMinor(@Valid @ModelAttribute("model") Minor minor, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			minor.setDepartmentList(departmentList);
			return "admin/registerminor";
		}
		
		try {
			minorService.save(minor);
			return "redirect:/admin/searchminors?successMessage=registered";
		} catch (Exception e) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			minor.setDepartmentList(departmentList);
			minor.setErrorMessage("Duplication Error! Please check MinorId.");
			return "admin/registerminor";
		}
	}
	
	@GetMapping("updateminor/{id}")
	public String getExistMinor(@PathVariable("id") String minorId,Model model) {
		Minor existMinor = new Minor();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		existMinor.setMinorId(minorId);
		existMinor = minorService.findMinor(existMinor).get(0);
		existMinor.setDepartmentList(departmentList);
		model.addAttribute("model",existMinor);
		return "admin/updateminor";
	}
	
	@PostMapping("updateminor")
	public String updateMinor(@Valid @ModelAttribute("model") Minor minor, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			minor.setDepartmentList(departmentList);
			return "admin/updateminor";
		}
		
		minorService.update(minor);
		return "redirect:/admin/searchminors?successMessage=updated";
	}
	
	@GetMapping("deleteminor/{id}")
	public String deleteMinor(@PathVariable("id") String minorId) {
		minorService.delete(minorId);
		return "redirect:/admin/searchminors?successMessage=deleted";
	}
}
