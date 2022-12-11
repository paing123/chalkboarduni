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

import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;
import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.service.CourseSectionService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;
import edu.chalkboarduni.uniregistrationsystem.service.EnrollmentService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentService;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;

@Controller
@RequestMapping("admin")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
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
    public void initModel(Enrollment Enrollment, Model model) {
        model.addAttribute("model", Enrollment);
    }
	
	@RequestMapping(value = {"enrollments"})
	public String getEnrollmentList(Enrollment enrollment) {
		List<Student> studentList = studentService.findStudent(new Student());
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		
		enrollment.setStudentList(studentList);
		enrollment.setCourseList(courseList);
		enrollment.setCourseSectionList(courseSectionList);
		return "admin/enrollments";
	}
	
	@RequestMapping(value = {"searchenrollments"})
	public String searchEnrollmentList(Enrollment enrollment) {
		List<Enrollment> enrollmentList = enrollmentService.findEnrollment(enrollment);
		List<Student> studentList = studentService.findStudent(new Student());
		List<Course> courseList = courseService.findCourse(new Course());
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		
		for (Enrollment existEnrollment : enrollmentList) {
			existEnrollment.setTimeWindow(DateTimeFormatter.changeDateFormatForTimeSlot(existEnrollment.getTimeWindow()));
			existEnrollment.setEnrollmentDate(DateTimeFormatter.changeDateFormat(existEnrollment.getEnrollmentDate()));
		}
		
		enrollment.setEnrollmentList(enrollmentList);
		enrollment.setStudentList(studentList);
		enrollment.setCourseList(courseList);
		enrollment.setCourseSectionList(courseSectionList);
		return "admin/enrollments";
	}
	
//	@GetMapping(value = {"registerenrollment"})
//	public String getEnrollmentForm(Enrollment enrollment,Model model) {
//		enrollment = new Enrollment();
//		List<Student> studentList = studentService.findStudent(new Student());
//		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
//		enrollment.setStudentList(studentList);
//		enrollment.setCourseSectionList(courseSectionList);
//		model.addAttribute("model",enrollment);
//		return "admin/registerenrollment";
//	}
//	
//	@PostMapping(value = {"registerenrollment"})
//	public String registerEnrollment(@Valid @ModelAttribute("model") Enrollment enrollment,BindingResult bindingResult) {
//		
//		if (bindingResult.hasErrors()) {
//			List<Student> studentList = studentService.findStudent(new Student());
//			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
//			enrollment.setStudentList(studentList);
//			enrollment.setCourseSectionList(courseSectionList);
//			return "admin/registerenrollment";
//		}
//		
//		try {
//			enrollmentService.save(enrollment);
//			return "redirect:/enrollments";
//		} catch (Exception e) {
//			List<Student> studentList = studentService.findStudent(new Student());
//			List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
//			enrollment.setStudentList(studentList);
//			enrollment.setCourseSectionList(courseSectionList);
//			enrollment.setErrorMessage("Duplication Error! Please check Student and CourseSection.");
//			return "admin/registerenrollment";
//		}
//	}
		
	@RequestMapping("deleteenrollment")
	public String deleteEnrollment(Enrollment enrollment) {
		enrollmentService.delete(enrollment);
		return "redirect:/enrollments";
	}
}
