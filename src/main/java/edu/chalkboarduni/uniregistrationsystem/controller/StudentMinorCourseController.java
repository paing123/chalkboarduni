package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;
import edu.chalkboarduni.uniregistrationsystem.model.MinorRequirement;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;
import edu.chalkboarduni.uniregistrationsystem.model.StudentMinor;
import edu.chalkboarduni.uniregistrationsystem.service.EnrollmentService;
import edu.chalkboarduni.uniregistrationsystem.service.MinorRequirementService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentHistoryService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentMinorService;

@Controller
@RequestMapping("student")
public class StudentMinorCourseController {
	
	@Autowired
	private StudentMinorService studentMinorService;
	
	@Autowired
	private MinorRequirementService minorRequirementService;
	
	@Autowired
	private StudentHistoryService studentHistoryService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@ModelAttribute
    public void initModel(MinorRequirement minorRequirement, Model model) {
        model.addAttribute("model", minorRequirement);
    }
	
	@RequestMapping(value = {"minorcourses"})
	public String getStudentMinorCourseList(MinorRequirement minorRequirement, HttpSession session) {
		
		UserDto currentStudent = (UserDto)session.getAttribute("currentUser");
		
		//get student minor
		StudentMinor stuMinor = new StudentMinor();
		stuMinor.setStudentId(currentStudent.getUserId());
		
		if(studentMinorService.findStudentMinor(stuMinor).size() > 0) {
			stuMinor = studentMinorService.findStudentMinor(stuMinor).get(0);
			
			//get required course for minor
			MinorRequirement minRequirement = new MinorRequirement();
			minRequirement.setMinorId(stuMinor.getMinorId());
			List<MinorRequirement> minorRequirements = minorRequirementService.findMinorRequirement(minRequirement);
			minorRequirement.setMinorRequirementList(minorRequirements);
			
			//get student history
			StudentHistory studHistory = new StudentHistory();
			studHistory.setStudentId(currentStudent.getUserId());
			List<StudentHistory> stuHistoryList = studentHistoryService.findStudentHistory(studHistory);
			
			for (MinorRequirement minorReq : minorRequirements) {
				
				//check enrollment
				Enrollment enrollment = new Enrollment();
				enrollment.setStudentId(currentStudent.getUserId());
				enrollment.setCourseId(minorReq.getCourseId());
				enrollment.setSemesterYear("Fall2022");
				List<Enrollment> enrollmentList = enrollmentService.findEnrollment(enrollment);
				
				if(enrollmentList.size() != 0) {
					minorReq.setStatus("In progress");
					continue;
				}
				
				
				for (StudentHistory studentHistory : stuHistoryList) {
					if(minorReq.getCourseId().equals(studentHistory.getCourseId())) {
						minorReq.setStatus("Passed");
						break;
					}
					minorReq.setStatus("Required");
				}
				
				if(stuHistoryList.size() == 0) {
					minorReq.setStatus("Required");
				}
			}
		}
		
		
		return "student/studentminorcourses";
	}
}
