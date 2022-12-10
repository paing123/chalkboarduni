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

import edu.chalkboarduni.uniregistrationsystem.model.FacultyHistory;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyHistoryService;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseSectionService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;

@Controller
public class FacultyHistoryController {

	@Autowired
	private FacultyHistoryService facultyHistoryService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseSectionService courseSectionService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(FacultyHistory facultyHistory, Model model) {
        model.addAttribute("model", facultyHistory);
    }
	
	@RequestMapping(value = {"/admin/facultyhistories"})
	public String getFacultyHistoryListForAdmin(FacultyHistory facultyHistory) {
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		facultyHistory.setCourseSectionList(courseSectionList);
		facultyHistory.setCourseList(courseList);
		facultyHistory.setFacultyList(facultyList);
		return "admin/facultyhistories";
	}
	
	@RequestMapping(value = {"/faculty/facultyhistories"})
	public String getFacultyHistoryList(FacultyHistory facultyHistory, HttpSession session) {
		
		UserDto currentUser = (UserDto) session.getAttribute("currentUser");
		facultyHistory.setFacultyId(currentUser.getUserId());
		
		List<FacultyHistory> facultyHistoryList = facultyHistoryService.findFacultyHistory(facultyHistory);
		
		facultyHistory.setFacultyHistoryList(facultyHistoryList);
		return "faculty/facultyhistories";
	}
	
	@RequestMapping(value = {"/admin/searchfacultyhistories"})
	public String searchFacultyHistoryList(FacultyHistory facultyHistory) {
		List<FacultyHistory> FacultyHistoryList = facultyHistoryService.findFacultyHistory(facultyHistory);
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		facultyHistory.setFacultyHistoryList(FacultyHistoryList);
		facultyHistory.setCourseSectionList(courseSectionList);
		facultyHistory.setCourseList(courseList);
		facultyHistory.setFacultyList(facultyList);
		return "admin/facultyhistories";
	}
	
	@GetMapping(value = {"/admin/registerfacultyhistory"})
	public String getFacultyHistoryForm(FacultyHistory facultyHistory,Model model) {
		facultyHistory = new FacultyHistory();
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		facultyHistory.setCourseSectionList(courseSectionList);
		facultyHistory.setFacultyList(facultyList);
		model.addAttribute("model",facultyHistory);
		return "admin/registerfacultyhistory";
	}
	
	@PostMapping(value = {"/admin/registerfacultyhistory"})
	public String registerFacultyHistory(@Valid @ModelAttribute("model") FacultyHistory facultyHistory, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			facultyHistory.setCourseSectionList(courseSectionList);
			facultyHistory.setFacultyList(facultyList);
			return "admin/registerfacultyhistory";
		}
		
		try {
			facultyHistoryService.save(facultyHistory);
			return "redirect:/admin/searchfacultyhistories?successMessage=registered";
		} catch (Exception e) {
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			facultyHistory.setCourseSectionList(courseSectionList);
			facultyHistory.setFacultyList(facultyList);
			facultyHistory.setErrorMessage("Duplication Error! Please check Faculty and CourseSection.");
			return "admin/registerfacultyhistory";
		}
		
		
	}
		
	@GetMapping("/admin/deletefacultyhistory/{facId}/{courseSectionId}")
	public String deleteFacultyHistory(@PathVariable("facId") Integer facultyId,@PathVariable("courseSectionId") Integer courseSectionId) {
		facultyHistoryService.delete(facultyId,courseSectionId);
		return "redirect:/admin/searchfacultyhistories?successMessage=deleted";
	}
}
