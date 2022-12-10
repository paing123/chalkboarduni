package edu.chalkboarduni.uniregistrationsystem.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;
import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;
import edu.chalkboarduni.uniregistrationsystem.service.CourseSectionService;
import edu.chalkboarduni.uniregistrationsystem.service.EnrollmentService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentHistoryService;

@Controller
@RequestMapping("student")
public class StudentEnrollmentHistoryController {

	@Autowired
	private EnrollmentService enrollmentSerive;
	
	@Autowired
	private CourseSectionService courseSectionSerive;
	
	@Autowired
	private StudentHistoryService studentHistoryService; 
	
	@ModelAttribute
    public void initModel(Enrollment enrollment, Model model) {
        model.addAttribute("model", enrollment);
    }
	
	@RequestMapping(value = {"enrollmenthistories"})
	public String searchStudentEnrollmentHistories(Enrollment enrollment, HttpSession session) throws Exception{
		
		UserDto currentStudent = (UserDto)session.getAttribute("currentUser");
		enrollment.setStudentId(currentStudent.getUserId());
		List<Enrollment> enrollments = enrollmentSerive.findEnrollment(enrollment);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (Enrollment enroll : enrollments) {
			CourseSection courseSection = new CourseSection();
			courseSection.setCourseSectionId(enroll.getCourseSectionId());
			courseSection = courseSectionSerive.findCourseSection(courseSection).get(0);
			String timeWindow = courseSection.getTimeWindow();
			String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			
			if(!sdf.parse(datetime).before(sdf.parse(timeWindow))) {
				enroll.setDisabledStatus("disabled");
			}
		}
		
		enrollment.setEnrollmentList(enrollments);
		return "student/studentenrollmenthistories";
	}
	
	@RequestMapping("deleteenrollment")
	public String deleteEnrollment(Enrollment enrollment) {
		enrollmentSerive.delete(enrollment);
		return "redirect:/student/enrollmenthistories?successMessage=deleted";
	}
	
	@RequestMapping("studenthistories")
	public String getStudentHistories(Model model,HttpSession session) {
		
		UserDto currentStudent = (UserDto)session.getAttribute("currentUser");
		StudentHistory stuHistory = new StudentHistory();
		stuHistory.setStudentId(currentStudent.getUserId());
		List<StudentHistory> stuHistories = studentHistoryService.findStudentHistory(stuHistory);
		model.addAttribute("studentHistories",stuHistories);
		return "student/studenthistories";
	}
}
