package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;
import edu.chalkboarduni.uniregistrationsystem.model.MajorRequirement;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;
import edu.chalkboarduni.uniregistrationsystem.model.StudentMajor;
import edu.chalkboarduni.uniregistrationsystem.service.MajorRequirementService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentHistoryService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentMajorService;

@Controller
@RequestMapping("student")
public class StudentMajorCourseController {
	
	@Autowired
	private StudentMajorService studentMajorService;
	
	@Autowired
	private MajorRequirementService majorRequirementService;
	
	@Autowired
	private StudentHistoryService studentHistoryService;
	
	@ModelAttribute
    public void initModel(MajorRequirement majorRequirement, Model model) {
        model.addAttribute("model", majorRequirement);
    }
	
	@RequestMapping(value = {"majorcourses"})
	public String searchCourseSectionList(MajorRequirement majorRequirement, HttpSession session) {
		
		UserDto currentStudent = (UserDto)session.getAttribute("currentUser");
		
		//get student major
		StudentMajor stuMajor = new StudentMajor();
		stuMajor.setStudentId(currentStudent.getUserId());
		stuMajor = studentMajorService.findStudentMajor(stuMajor).get(0);
		
		//get required course for major
		MajorRequirement majRequirement = new MajorRequirement();
		majRequirement.setMajorId(stuMajor.getMajorId());
		List<MajorRequirement> majorRequirements = majorRequirementService.findMajorRequirement(majRequirement);
		majorRequirement.setMajorRequirementList(majorRequirements);
		
		//get student history
		StudentHistory studHistory = new StudentHistory();
		studHistory.setStudentId(currentStudent.getUserId());
		List<StudentHistory> stuHistoryList = studentHistoryService.findStudentHistory(studHistory);
		
		for (MajorRequirement majorReq : majorRequirements) {
			for (StudentHistory studentHistory : stuHistoryList) {
				if(majorReq.getCourseId().equals(studentHistory.getCourseId())) {
					majorReq.setStatus("Passed");
					break;
				}
				majorReq.setStatus("Required");
			}
			
			if(stuHistoryList.size() == 0) {
				majorReq.setStatus("Required");
			}
		}
		
		return "student/studentmajorcourses";
	}
}
