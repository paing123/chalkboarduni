package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.chalkboarduni.uniregistrationsystem.model.Major;
import edu.chalkboarduni.uniregistrationsystem.model.MajorRequirement;
import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.service.MajorService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;
import edu.chalkboarduni.uniregistrationsystem.service.MajorRequirementService;

@Controller
@RequestMapping("admin")
public class MajorRequirementController {

	@Autowired
	private MajorRequirementService majorRequirementService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MajorService majorService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(MajorRequirement majorRequirement, Model model) {
        model.addAttribute("model", majorRequirement);
    }
	
	@RequestMapping(value = {"majorrequirementdetail"})
	public String getMajorRequirementDetail(MajorRequirement model) {
		List<MajorRequirement> majorRequirementList = majorRequirementService.findMajorRequirement(model);
		List<Major> majorList = majorService.findMajor(new Major());
		List<Course> courseList = courseService.findCourse(new Course());
		model.setMajorRequirementList(majorRequirementList);
		model.setCourseList(courseList);
		model.setMajorList(majorList);
		return "admin/majorrequirementdetail";
	}
	
	@PostMapping(value = {"registermajorrequirement"})
	public String getMajorForm(@Valid @ModelAttribute("model") MajorRequirement majorRequirement,
								RedirectAttributes redirectAttributes, BindingResult bindingResult) {
		
		List<Course> courseList = courseService.findCourse(new Course());
		majorRequirement.setCourseList(courseList);
		
		if (bindingResult.hasErrors()) {
			return "admin/majorrequirementdetail";
		}
		
		try {
			majorRequirementService.save(majorRequirement);
			redirectAttributes.addAttribute("majorId", majorRequirement.getMajorId());
			redirectAttributes.addAttribute("majorName", majorRequirement.getMajorName());
			return "redirect:/admin/majorrequirementdetail?successMessage=registered";
		} catch (Exception e) {
			majorRequirement.setCourseId(null);
			List<MajorRequirement> majorRequirementList = majorRequirementService.findMajorRequirement(majorRequirement);
			majorRequirement.setMajorRequirementList(majorRequirementList);
			majorRequirement.setErrorMessage("Duplication Error! Please check Course.");
			return "admin/majorrequirementdetail";
		}
	}
	
	@RequestMapping(value = {"majorrequirements"})
	public String getMajorRequirementList(MajorRequirement majorRequirement) {
		List<MajorRequirement> majorRequirementList = majorRequirementService.findMajorRequirement(majorRequirement);
		List<Major> majorList = majorService.findMajor(new Major());
		List<Course> courseList = courseService.findCourse(new Course());
		majorRequirement.setMajorRequirementList(majorRequirementList);
		majorRequirement.setMajorList(majorList);
		majorRequirement.setCourseList(courseList);
		return "admin/majorrequirements";
	}
	
	@RequestMapping("deletemajorrequirement")
	public String deleteMajor(MajorRequirement model,RedirectAttributes redirectAttributes) {
		majorRequirementService.delete(model);
		redirectAttributes.addAttribute("majorId", model.getMajorId());
		redirectAttributes.addAttribute("majorName", model.getMajorName());
		return "redirect:/admin/majorrequirementdetail?successMessage=deleted";
	}
}
