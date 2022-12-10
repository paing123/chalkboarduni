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

import edu.chalkboarduni.uniregistrationsystem.model.Minor;
import edu.chalkboarduni.uniregistrationsystem.model.MinorRequirement;
import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.service.MinorService;
import edu.chalkboarduni.uniregistrationsystem.service.MinorRequirementService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;

@Controller
@RequestMapping("admin")
public class MinorRequirementController {

	@Autowired
	private MinorRequirementService minorRequirementService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MinorService minorService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(MinorRequirement minorRequirement, Model model) {
        model.addAttribute("model", minorRequirement);
    }
	
	@RequestMapping(value = {"minorrequirementdetail"})
	public String getMinorRequirementDetail(MinorRequirement model) {
		List<MinorRequirement> minorRequirementList = minorRequirementService.findMinorRequirement(model);
		List<Minor> minorList = minorService.findMinor(new Minor());
		List<Course> courseList = courseService.findCourse(new Course());
		model.setMinorRequirementList(minorRequirementList);
		model.setCourseList(courseList);
		model.setMinorList(minorList);
		return "admin/minorrequirementdetail";
	}
	
	@PostMapping(value = {"registerminorrequirement"})
	public String getMinorForm(@Valid @ModelAttribute("model") MinorRequirement minorRequirement,
							   RedirectAttributes redirectAttributes, BindingResult bindingResult) {
				
		List<Course> courseList = courseService.findCourse(new Course());
		minorRequirement.setCourseList(courseList);
		
		if (bindingResult.hasErrors()) {
			return "admin/minorrequirementdetail";
		}
		
		try {
			minorRequirementService.save(minorRequirement);
			redirectAttributes.addAttribute("minorId", minorRequirement.getMinorId());
			redirectAttributes.addAttribute("minorName", minorRequirement.getMinorName());
			return "redirect:/admin/minorrequirementdetail?successMessage=registered";
		} catch (Exception e) {
			minorRequirement.setCourseId(null);
			List<MinorRequirement> minorRequirementList = minorRequirementService.findMinorRequirement(minorRequirement);
			minorRequirement.setMinorRequirementList(minorRequirementList);
			minorRequirement.setErrorMessage("Duplication Error! Please check CourseId.");
			return "admin/minorrequirementdetail";
		}
	}
	
	@RequestMapping(value = {"minorrequirements"})
	public String getMinorRequirementList(MinorRequirement minorRequirement) {
		List<MinorRequirement> minorRequirementList = minorRequirementService.findMinorRequirement(minorRequirement);
		List<Minor> minorList = minorService.findMinor(new Minor());
		List<Course> courseList = courseService.findCourse(new Course());
		minorRequirement.setMinorRequirementList(minorRequirementList);
		minorRequirement.setMinorList(minorList);
		minorRequirement.setCourseList(courseList);
		return "admin/minorrequirements";
	}
	
	@RequestMapping("deleteminorrequirement")
	public String deleteMinor(MinorRequirement model,RedirectAttributes redirectAttributes) {
		minorRequirementService.delete(model);
		redirectAttributes.addAttribute("minorId", model.getMinorId());
		redirectAttributes.addAttribute("minorName", model.getMinorName());
		return "redirect:/admin/minorrequirementdetail?successMessage=deleted";
	}
}
