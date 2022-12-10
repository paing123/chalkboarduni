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

import edu.chalkboarduni.uniregistrationsystem.model.Prerequisites;
import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.service.PrerequisitesService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;

@Controller
@RequestMapping("admin")
public class PrerequisitesController {

	@Autowired
	private PrerequisitesService prerequisitesService;
	
	@Autowired
	private CourseService courseService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Prerequisites prerequisites, Model model) {
        model.addAttribute("model", prerequisites);
    }
	
	@RequestMapping(value = {"prerequisitess"})
	public String getPrerequisitesList(Prerequisites prerequisites) {
		List<Course> courseList = courseService.findCourse(new Course());
		prerequisites.setCourseList(courseList);
		return "admin/prerequisitess";
	}
	
	@RequestMapping(value = {"searchprerequisitess"})
	public String searchPrerequisitesList(Prerequisites prerequisites) {
		List<Prerequisites> prerequisitesList = prerequisitesService.findPrerequisites(prerequisites);
		List<Course> courseList = courseService.findCourse(new Course());
		prerequisites.setPrerequisitesList(prerequisitesList);
		prerequisites.setCourseList(courseList);
		return "admin/prerequisitess";
	}
	
	@GetMapping(value = {"registerprerequisites"})
	public String getPrerequisitesForm(Prerequisites prerequisites,Model model) {
		prerequisites = new Prerequisites();
		List<Course> courseList = courseService.findCourse(new Course());
		prerequisites.setCourseList(courseList);
		model.addAttribute("model",prerequisites);
		return "admin/registerprerequisites";
	}
	
	@PostMapping(value = {"registerprerequisites"})
	public String registerPrerequisites(@Valid @ModelAttribute("model") Prerequisites prerequisites, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Course> courseList = courseService.findCourse(new Course());
			prerequisites.setCourseList(courseList);
			return "admin/registerprerequisites";
		}
		
		if(prerequisites.getCourseId().equals(prerequisites.getRequiredCourseId())) {
			List<Course> courseList = courseService.findCourse(new Course());
			prerequisites.setCourseList(courseList);
			prerequisites.setErrorMessage("Course and RequiredCourse should not be same.");
			return "admin/registerprerequisites";
		}
		
		Prerequisites checkPrereq = new Prerequisites();
		checkPrereq.setRequiredCourseId(prerequisites.getRequiredCourseId());
		checkPrereq.setCourseId(prerequisites.getCourseId());
		
		List<Prerequisites> checkPreReqList = prerequisitesService.findPrerequisites(checkPrereq);
		if(checkPreReqList.size() > 0) {
			List<Course> courseList = courseService.findCourse(new Course());
			prerequisites.setCourseList(courseList);
			prerequisites.setErrorMessage("Duplication Error! Please check Course and RequiredCourse.");
			return "admin/registerprerequisites";
		}
		
		prerequisitesService.save(prerequisites);
		return "redirect:/admin/searchprerequisitess?successMessage=registered";	
	}
	
	@GetMapping("updateprerequisites/{id}")
	public String getExistPrerequisites(@PathVariable("id") Integer prerequisitesId,Model model) {
		Prerequisites existPrerequisites = new Prerequisites();
		List<Course> CourseList = courseService.findCourse(new Course());
		existPrerequisites.setPrereqId(prerequisitesId);
		existPrerequisites = prerequisitesService.findPrerequisites(existPrerequisites).get(0);
		existPrerequisites.setCourseList(CourseList);
		model.addAttribute("model",existPrerequisites);
		return "admin/updateprerequisites";
	}
	
	@PostMapping("updateprerequisites")
	public String updatePrerequisites(@Valid @ModelAttribute("model") Prerequisites prerequisites, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Course> courseList = courseService.findCourse(new Course());
			prerequisites.setCourseList(courseList);
			return "admin/updateprerequisites";
		}
		
		if(prerequisites.getCourseId().equals(prerequisites.getRequiredCourseId())) {
			List<Course> courseList = courseService.findCourse(new Course());
			prerequisites.setCourseList(courseList);
			prerequisites.setErrorMessage("Course and RequiredCourse should not be same.");
			return "admin/updateprerequisites";
		}
		
		Prerequisites checkPrereq = new Prerequisites();
		checkPrereq.setRequiredCourseId(prerequisites.getRequiredCourseId());
		checkPrereq.setCourseId(prerequisites.getCourseId());
		
		List<Prerequisites> checkPreReqList = prerequisitesService.findPrerequisites(checkPrereq);
		if(checkPreReqList.size() > 1) {
			List<Course> courseList = courseService.findCourse(new Course());
			prerequisites.setCourseList(courseList);
			prerequisites.setErrorMessage("Duplication Error! Please check Course and RequiredCourse.");
			return "admin/updateprerequisites";
		}
		
		prerequisitesService.update(prerequisites);
		return "redirect:/admin/searchprerequisitess?successMessage=updated";
	}
	
	@GetMapping("deleteprerequisites/{id}")
	public String deletePrerequisites(@PathVariable("id") Integer prerequisitesId) {
		prerequisitesService.delete(prerequisitesId);
		return "redirect:/admin/searchprerequisitess?successMessage=deleted";
	}
}
