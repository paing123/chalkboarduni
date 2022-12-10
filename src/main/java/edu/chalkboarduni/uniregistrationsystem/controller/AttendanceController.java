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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
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
@RequestMapping("admin")
public class AttendanceController {

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
	
	@GetMapping(value = {"attendances"})
	public String getAttendanceList(Attendance attendance) {
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Student> studentList = studentService.findStudent(new Student());
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		attendance.setFacultyList(facultyList);
		attendance.setStudentList(studentList);
		attendance.setCourseList(courseList);
		attendance.setCourseSectionList(courseSectionList);
		return "admin/attendances";
	}
	
	@RequestMapping(value = {"searchattendances"})
	public String searchAttendanceList(Attendance attendance) {
		List<Attendance> attendanceList = attendanceService.findAttendance(attendance);
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Student> studentList = studentService.findStudent(new Student());
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		for (Attendance att : attendanceList) {
			att.setDate(DateTimeFormatter.changeDateFormat(att.getDate()));
		}
		attendance.setAttendanceList(attendanceList);
		attendance.setFacultyList(facultyList);
		attendance.setStudentList(studentList);
		attendance.setCourseList(courseList);
		attendance.setCourseSectionList(courseSectionList);
		return "admin/attendances";
	}
	
	@GetMapping(value = {"registerattendance"})
	public String getAttendanceForm(Attendance attendance,Model model) {
		attendance = new Attendance();
		List<Attendance> attendanceList = attendanceService.findAttendance(attendance);
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Student> studentList = studentService.findStudent(new Student());
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		attendance.setAttendanceList(attendanceList);
		attendance.setFacultyList(facultyList);
		attendance.setStudentList(studentList);
		attendance.setCourseList(courseList);
		attendance.setCourseSectionList(courseSectionList);
		model.addAttribute("model",attendance);
		return "admin/registerattendance";
	}
	
	@PostMapping(value = {"registerattendance"})
	public String registerAttendance(@Valid @ModelAttribute("model") Attendance attendance,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
			attendance.setStudentList(studentList);
			attendance.setCourseSectionList(courseSectionList);
			return "admin/registerattendance";
		}
		
		try {
			attendanceService.save(attendance);
			return "redirect:/admin/searchattendances?successMessage=registered";
		} catch (Exception e) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
			attendance.setStudentList(studentList);
			attendance.setCourseSectionList(courseSectionList);
			attendance.setErrorMessage("Duplication Error! Please check Student, CourseSection and Date.");
			return "admin/registerattendance";
		}
		
		
	}
	
	@PostMapping("updateattendanceform")
	public String getExistAttendance(Attendance attendance, Model model) {
		
		List<Student> studentList = studentService.findStudent(new Student());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		attendance = attendanceService.findAttendance(attendance).get(0);
		attendance.setCourseSectionList(courseSectionList);
		attendance.setStudentList(studentList);
		model.addAttribute("model", attendance);
		return "admin/updateattendance";
			
	}
	
	@PostMapping("updateattendance")
	public String updateExistAttendance(@Valid @ModelAttribute("model") Attendance attendance,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Student> studentList = studentService.findStudent(new Student());
			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
			attendance.setStudentList(studentList);
			attendance.setCourseSectionList(courseSectionList);
			return "admin/updateattendance";
		}
		
		attendanceService.update(attendance);
		return "redirect:/admin/searchattendances?successMessage=updated";
	}
	
		
	@PostMapping("deleteattendance")
	public String deleteAttendance(Attendance attendance) {
		attendanceService.delete(attendance);
		return "redirect:/admin/searchattendances?successMessage=deleted";
	}
}
