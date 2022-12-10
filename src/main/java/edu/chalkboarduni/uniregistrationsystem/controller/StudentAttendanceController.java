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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Attendance;
import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;
import edu.chalkboarduni.uniregistrationsystem.service.AttendanceService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseSectionService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;

@Controller
@RequestMapping("faculty")
public class StudentAttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private FacultyService facultyService;
	
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
    public void initModel(Attendance attendance, Model model) {
        model.addAttribute("model", attendance);
    }
	
	@GetMapping(value = {"studentattendances"})
	public String getAttendanceList(Attendance attendance, HttpSession session) {
		
		UserDto currentFaculty = (UserDto)session.getAttribute("currentUser");
		
		CourseSection courseSection = new CourseSection();
		courseSection.setFacultyId(currentFaculty.getUserId());
		
		List<Student> studentList = studentService.findStudent(new Student());
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		attendance.setStudentList(studentList);
		attendance.setCourseList(courseList);
		attendance.setCourseSectionList(courseSectionList);
		return "faculty/studentattendances";
	}
	
	@RequestMapping(value = {"searchstudentattendances"})
	public String searchAttendanceList(Attendance attendance, HttpSession session) {
		
		UserDto currentFaculty = (UserDto)session.getAttribute("currentUser");
		
		CourseSection courseSection = new CourseSection();
		courseSection.setFacultyId(currentFaculty.getUserId());
		
		attendance.setFacultyId(currentFaculty.getUserId());
		
		List<Attendance> attendanceList = attendanceService.findAttendance(attendance);
		List<Student> studentList = studentService.findStudent(new Student());
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		
		for (Attendance att : attendanceList) {
			att.setDate(DateTimeFormatter.changeDateFormat(att.getDate()));
		}
		
		attendance.setAttendanceList(attendanceList);
		attendance.setStudentList(studentList);
		attendance.setCourseList(courseList);
		attendance.setCourseSectionList(courseSectionList);
		return "faculty/studentattendances";
	}
	
	@GetMapping(value = {"registerstudentattendance"})
	public String getAttendanceForm(Attendance attendance, HttpSession session, Model model) {
		
		UserDto currentFaculty = (UserDto)session.getAttribute("currentUser");
		
		CourseSection courseSection = new CourseSection();
		courseSection.setFacultyId(currentFaculty.getUserId());
		
		attendance = new Attendance();
		attendance.setFacultyId(currentFaculty.getUserId());
		
		List<Attendance> attendanceList = attendanceService.findAttendance(attendance);
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Student> studentList = studentService.findStudent(new Student());
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		attendance.setAttendanceList(attendanceList);
		attendance.setFacultyList(facultyList);
		attendance.setStudentList(studentList);
		attendance.setCourseList(courseList);
		attendance.setCourseSectionList(courseSectionList);
		model.addAttribute("model",attendance);
		return "faculty/registerstudentattendance";
	}
	
	@PostMapping(value = {"registerstudentattendance"})
	public String registerAttendance(@Valid @ModelAttribute("model") Attendance attendance,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			CourseSection courseSection = new CourseSection();
			courseSection.setFacultyId(attendance.getFacultyId());
			
			List<Student> studentList = studentService.findStudent(new Student());
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
			attendance.setStudentList(studentList);
			attendance.setCourseSectionList(courseSectionList);
			return "faculty/registerstudentattendance";
		}
		
		try {
			attendanceService.save(attendance);
			return "redirect:/faculty/searchstudentattendances?successMessage=registered";
		} catch (Exception e) {
			
			CourseSection courseSection = new CourseSection();
			courseSection.setFacultyId(attendance.getFacultyId());
			
			List<Student> studentList = studentService.findStudent(new Student());
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
			attendance.setStudentList(studentList);
			attendance.setCourseSectionList(courseSectionList);
			attendance.setErrorMessage("Duplication Error! Please check Student, CourseSection and Date.");
			return "faculty/registerstudentattendance";
		}
	}
	
	@PostMapping("updatestudentattendanceform")
	public String getExistAttendance(Attendance attendance, Model model) {
		
		CourseSection courseSection = new CourseSection();
		courseSection.setFacultyId(attendance.getFacultyId());
		
		List<Student> studentList = studentService.findStudent(new Student());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		attendance = attendanceService.findAttendance(attendance).get(0);
		attendance.setCourseSectionList(courseSectionList);
		attendance.setStudentList(studentList);
		model.addAttribute("model", attendance);
		return "faculty/updatestudentattendance";
			
	}
	
	@PostMapping("updatestudentattendance")
	public String updateExistAttendance(@Valid @ModelAttribute("model") Attendance attendance, BindingResult bindingResult, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			
			UserDto currentFaculty = (UserDto)session.getAttribute("currentUser");
			
			CourseSection courseSection = new CourseSection();
			courseSection.setFacultyId(currentFaculty.getUserId());
			
			List<Student> studentList = studentService.findStudent(new Student());
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
			attendance.setStudentList(studentList);
			attendance.setCourseSectionList(courseSectionList);
			return "faculty/updatestudentattendance";
		}
		
		attendanceService.update(attendance);
		return "redirect:/faculty/searchstudentattendances?successMessage=updated";
	}
	
		
	@PostMapping("deletestudentattendance")
	public String deleteAttendance(Attendance attendance) {
		attendanceService.delete(attendance);
		return "redirect:/faculty/searchstudentattendances?successMessage=deleted";
	}
}
