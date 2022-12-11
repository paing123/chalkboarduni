package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

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

import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.service.StudentHistoryService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseSectionService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;

@Controller
@RequestMapping("faculty")
public class FacultyStudentHistoryController {

	@Autowired
	private StudentHistoryService studentHistoryService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseSectionService courseSectionService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(StudentHistory studentHistory, Model model) {
        model.addAttribute("model", studentHistory);
    }
	
	@RequestMapping(value = {"studenthistories"})
	public String getStudentHistoryList(StudentHistory studentHistory, HttpSession session) {
		
		UserDto currentFaculty = (UserDto)session.getAttribute("currentUser");
		
		Student student = new Student();
		CourseSection courseSection = new CourseSection();
		courseSection.setFacultyId(currentFaculty.getUserId());
		
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		List<Student> studentList = studentService.findStudent(student);
		
		studentHistory.setFacultyId(currentFaculty.getUserId());
		studentHistory.setCourseSectionList(courseSectionList);
		studentHistory.setCourseList(courseList);
		studentHistory.setStudentList(studentList);
		
		return "faculty/studenthistories";
	}
	
	@RequestMapping(value = {"searchstudenthistories"})
	public String searchStudentHistoryList(StudentHistory studentHistory, HttpSession session) {
		
		UserDto currentFaculty = (UserDto)session.getAttribute("currentUser");
		studentHistory.setFacultyId(currentFaculty.getUserId());
		
		Student student = new Student();
		
		List<StudentHistory> studentHistoryList = studentHistoryService.findStudentHistory(studentHistory);
		List<Course> courseList = courseService.findCourse(new Course());
		
		CourseSection courseSection = new CourseSection();
		courseSection.setFacultyId(studentHistory.getFacultyId());
		
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		List<Student> studentList = studentService.findStudent(student);
		studentHistory.setStudentHistoryList(studentHistoryList);
		studentHistory.setCourseSectionList(courseSectionList);
		studentHistory.setCourseList(courseList);
		studentHistory.setStudentList(studentList);
		return "faculty/studenthistories";
	}
	
	@GetMapping(value = {"registerstudenthistory"})
	public String getStudentHistoryForm(StudentHistory studentHistory,HttpSession session, Model model) {
		
		UserDto currentFaculty = (UserDto) session.getAttribute("currentUser");
		
		studentHistory = new StudentHistory();
		CourseSection courseSection = new CourseSection();
		courseSection.setFacultyId(currentFaculty.getUserId());
		Student student = new Student();
		
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		List<Student> studentList = studentService.findStudent(student);
		studentHistory.setFacultyId(currentFaculty.getUserId());
		studentHistory.setCourseSectionList(courseSectionList);
		studentHistory.setStudentList(studentList);
		model.addAttribute("model",studentHistory);
		return "faculty/registerstudenthistory";
	}
	
	@PostMapping(value = {"registerstudenthistory"})
	public String registerStudentHistory(@Valid @ModelAttribute("model") StudentHistory studentHistory, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			CourseSection courseSection = new CourseSection();
			courseSection.setFacultyId(studentHistory.getFacultyId());
			Student student = new Student();
			
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
			List<Student> studentList = studentService.findStudent(student);
			studentHistory.setCourseSectionList(courseSectionList);
			studentHistory.setStudentList(studentList);
			return "faculty/registerstudenthistory";
		}
		
		try {
			studentHistoryService.save(studentHistory);
			return "redirect:/faculty/searchstudenthistories?successMessage=registered";
		} catch (Exception e) {
			
			CourseSection courseSection = new CourseSection();
			courseSection.setFacultyId(studentHistory.getFacultyId());
			Student student = new Student();
			
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
			List<Student> studentList = studentService.findStudent(student);
			studentHistory.setCourseSectionList(courseSectionList);
			studentHistory.setStudentList(studentList);
			studentHistory.setErrorMessage("Duplication Error! Please check StudentId and CourseSectionId.");
			return "faculty/registerstudenthistory";
		}
	}
	
	@PostMapping("updatestudenthistoryform")
	public String getExistStudentHistory(StudentHistory studentHistory, Model model) {
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		List<Student> studentList = studentService.findStudent(new Student());
		studentHistory = studentHistoryService.findStudentHistory(studentHistory).get(0);
		studentHistory.setCourseSectionList(courseSectionList);
		studentHistory.setStudentList(studentList);
		model.addAttribute("model", studentHistory);
		return "faculty/updatestudenthistory";
	}
	
	@PostMapping("updatestudenthistory")
	public String updateStudentHistory(@Valid @ModelAttribute("model") StudentHistory studentHistory, BindingResult bindingResult, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			
			UserDto currentFaculty = (UserDto) session.getAttribute("currentUser");
			
			CourseSection courseSection = new CourseSection();
			courseSection.setFacultyId(currentFaculty.getUserId());
			
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
			List<Student> studentList = studentService.findStudent(new Student());
			studentHistory.setCourseSectionList(courseSectionList);
			studentHistory.setStudentList(studentList);
			return "faculty/updatestudenthistory";
		}
		
		try {
			studentHistoryService.update(studentHistory);
			return "redirect:/faculty/searchstudenthistories?successMessage=updated";
		} catch (Exception e) {
			
			UserDto currentFaculty = (UserDto) session.getAttribute("currentUser");
			
			CourseSection courseSection = new CourseSection();
			courseSection.setFacultyId(currentFaculty.getUserId());
			
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
			List<Student> studentList = studentService.findStudent(new Student());
			studentHistory.setCourseSectionList(courseSectionList);
			studentHistory.setStudentList(studentList);
			studentHistory.setErrorMessage("Duplication Error! Please check StudentId and CourseSectionId.");
			return "faculty/updatestudenthistory";
		}
	}
	
	@GetMapping("deletestudenthistory/{studentid}/{coursesectionid}")
	public String deleteStudentHistory(@PathVariable("studentid") Integer studentId,@PathVariable("coursesectionid") Integer courseSectionId) {
		studentHistoryService.delete(studentId,courseSectionId);
		return "redirect:/faculty/searchstudenthistories?successMessage=deleted";
	}
}
