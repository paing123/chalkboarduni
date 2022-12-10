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

import edu.chalkboarduni.uniregistrationsystem.model.Major;
import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.service.MajorService;
import edu.chalkboarduni.uniregistrationsystem.service.DepartmentService;

@Controller
@RequestMapping("admin")
public class MajorController {

	@Autowired
	private MajorService majorService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Major major, Model model) {
        model.addAttribute("model", major);
    }
	
	@RequestMapping(value = {"majors"})
	public String getMajorList(Major major) {
		List<Department> departmentList = departmentService.findDepartment(new Department());
		major.setDepartmentList(departmentList);
		return "admin/majors";
	}
	
	@RequestMapping(value = {"searchmajors"})
	public String searchMajorList(Major major) {
		List<Major> majorList = majorService.findMajor(major);
		List<Department> departmentList = departmentService.findDepartment(new Department());
		major.setMajorList(majorList);
		major.setDepartmentList(departmentList);
		return "admin/majors";
	}
	
	@GetMapping(value = {"registermajor"})
	public String getMajorForm(Major major,Model model) {
		major = new Major();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		major.setDepartmentList(departmentList);
		model.addAttribute("model",major);
		return "admin/registermajor";
	}
	
	@PostMapping(value = {"registermajor"})
	public String registerMajor(@Valid @ModelAttribute("model") Major major, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			major.setDepartmentList(departmentList);
			return "admin/registermajor";
		}
		
		try {
			majorService.save(major);
			return "redirect:/admin/searchmajors?successMessage=registered";
		} catch (Exception e) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			major.setDepartmentList(departmentList);
			major.setErrorMessage("Duplication Error! Please check MajorId.");
			return "admin/registermajor";
		}
	}
	
	@GetMapping("updatemajor/{id}")
	public String getExistMajor(@PathVariable("id") String majorId,Model model) {
		Major existMajor = new Major();
		List<Department> departmentList = departmentService.findDepartment(new Department());
		existMajor.setMajorId(majorId);
		existMajor = majorService.findMajor(existMajor).get(0);
		existMajor.setDepartmentList(departmentList);
		model.addAttribute("model",existMajor);
		return "admin/updatemajor";
	}
	
	@PostMapping("updatemajor")
	public String updateMajor(@Valid @ModelAttribute("model") Major major, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findDepartment(new Department());
			major.setDepartmentList(departmentList);
			return "admin/updatemajor";
		}
		majorService.update(major);
		return "redirect:/admin/searchmajors?successMessage=updated";
	}
	
	@GetMapping("deletemajor/{id}")
	public String deleteMajor(@PathVariable("id") String majorId) {
		majorService.delete(majorId);
		return "redirect:/admin/searchmajors?successMessage=deleted";
	}
}
