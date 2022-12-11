package edu.chalkboarduni.uniregistrationsystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Course;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;
import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.model.Hold;
import edu.chalkboarduni.uniregistrationsystem.model.Prerequisites;
import edu.chalkboarduni.uniregistrationsystem.model.Room;
import edu.chalkboarduni.uniregistrationsystem.model.Student;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHold;
import edu.chalkboarduni.uniregistrationsystem.model.TimeSlot;
import edu.chalkboarduni.uniregistrationsystem.service.CourseSectionService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;
import edu.chalkboarduni.uniregistrationsystem.service.DepartmentService;
import edu.chalkboarduni.uniregistrationsystem.service.EnrollmentService;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;
import edu.chalkboarduni.uniregistrationsystem.service.PrerequisitesService;
import edu.chalkboarduni.uniregistrationsystem.service.RoomService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentHistoryService;
import edu.chalkboarduni.uniregistrationsystem.service.StudentHoldService;
import edu.chalkboarduni.uniregistrationsystem.service.TimeSlotService;

@Controller
@RequestMapping("student")
public class StudentEnrollmentController {
	
	@Autowired
	private CourseSectionService courseSectionService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PrerequisitesService prerequisitesService;
	
	@Autowired
	private StudentHistoryService studentHistoryService;
	
	@Autowired
	private StudentHoldService studentHoldService;

	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(CourseSection courseSection, Model model) {
        model.addAttribute("model", courseSection);
    }
	
	@RequestMapping(value = {"coursesections"})
	public String searchCourseSectionList(CourseSection courseSection) {
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		List<Department> departmentList = departmentService.findDepartment(new Department());
		
		for (CourseSection courseSec : courseSectionList) {
			courseSec.setTimeWindow(edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter.changeDateFormatForTimeSlot(courseSec.getTimeWindow()));
		}
		
		courseSection.setCourseSectionList(courseSectionList);
		courseSection.setCourseList(courseList);
		courseSection.setFacultyList(facultyList);
		courseSection.setTimeSlotList(timeSlotList);
		courseSection.setRoomList(roomList);
		courseSection.setDepartmentList(departmentList);
		this.checkTimeWindow(courseSectionList);
		return "student/coursesections";
	}
	
	@GetMapping("enrollcoursesection/{id}")
	public String enrollCourseSection(@PathVariable("id") Integer courseSectionId,Model model,HttpSession session) {
		
		UserDto currentStudent = (UserDto)session.getAttribute("currentUser");
		
		CourseSection enrollCourseSection = new CourseSection();
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<Room> roomList = roomService.findRoom(new Room());
		List<Department> departmentList = departmentService.findDepartment(new Department());
		enrollCourseSection.setCourseList(courseList);
		enrollCourseSection.setFacultyList(facultyList);
		enrollCourseSection.setRoomList(roomList);
		enrollCourseSection.setDepartmentList(departmentList);
		enrollCourseSection.setCourseSectionId(courseSectionId);
		enrollCourseSection = courseSectionService.findCourseSection(enrollCourseSection).get(0);
		
		this.refreshCourseSection(enrollCourseSection);	
	
		String currentSemesterYear = "Fall2022";
		
		//check student hold
		StudentHold studentHold = new StudentHold();
		studentHold.setStudentId(currentStudent.getUserId());
		List<StudentHold> studentHolds = studentHoldService.findStudentHold(studentHold);
		if(studentHolds.size() > 0) {
			enrollCourseSection.setErrorMessage("You have a hold, hold type : "+studentHolds.get(0).getHoldType());
			this.cleanModel(enrollCourseSection);
			model.addAttribute("model", enrollCourseSection);
			return "student/coursesections";
		}
		
		//check remaining seats in course sections
		if(enrollCourseSection.getRemainingSeats() == 0) {
			enrollCourseSection.setErrorMessage("Sorry, there is no remaining seat and you can't enroll.");
			this.cleanModel(enrollCourseSection);
			model.addAttribute("model", enrollCourseSection);
			return "student/coursesections";
		}
		
		//check course already enroll or not
		Enrollment enrollment = new Enrollment();
		enrollment.setStudentId(currentStudent.getUserId());
		enrollment.setCourseSectionId(enrollCourseSection.getCourseSectionId());
		
		List<Enrollment> enrollmentList = enrollmentService.findEnrollment(enrollment);
		if (enrollmentList.size() > 0) {
			enrollCourseSection.setErrorMessage("You had already enrolled that course. Try another one.");
			this.cleanModel(enrollCourseSection);
			model.addAttribute("model", enrollCourseSection);
			return "student/coursesections";
		}

		//check prerequisities
		String resultMessage = this.checkPrerequisities(enrollCourseSection,currentStudent);
		if(!"ok".equals(resultMessage)) {
			enrollCourseSection.setErrorMessage(resultMessage);
			this.cleanModel(enrollCourseSection);
			model.addAttribute("model", enrollCourseSection);
			return "student/coursesections";
		}
		
		//check timeslot from enrollment
		Enrollment enrollmentTimeSlot = new Enrollment();
		enrollmentTimeSlot.setSemesterYear(currentSemesterYear);
		List<Enrollment> enrollments = enrollmentService.findEnrollment(enrollmentTimeSlot);
		for (Enrollment enroll : enrollments) {
			if(enroll.getTimeSlotId().equals(enrollCourseSection.getTimeSlotId())) {
				enrollCourseSection.setErrorMessage("You had already enrolled this period of time : "
													+enrollCourseSection.getDay()+", "+enrollCourseSection.getPeriod());
				this.cleanModel(enrollCourseSection);
				model.addAttribute("model", enrollCourseSection);
				return "student/coursesections";
			}
		}
		
		enrollmentService.save(enrollment);
		this.refreshCourseSection(enrollCourseSection);
		this.cleanModel(enrollCourseSection);
		enrollCourseSection.setSuccessMessage("You are successfully enrolled.");
		model.addAttribute("model", enrollCourseSection);
		return "student/coursesections";
	}
	
	private String checkPrerequisities(CourseSection courseSection, UserDto currentStudent) {
				
		String currentCourseId = courseSection.getCourseId();
		
		Prerequisites preReq = new Prerequisites();
		preReq.setCourseId(currentCourseId);
		List<Prerequisites> prerequisitiess = prerequisitesService.findPrerequisites(preReq);
		
		StudentHistory stuHistory = new StudentHistory();
		stuHistory.setStudentId(currentStudent.getUserId());
		List<StudentHistory> stuHistoryList = studentHistoryService.findStudentHistory(stuHistory);
		
		//if(prerequisitiess.size() > 1) {
			
			for (Prerequisites prerequisites : prerequisitiess) {
				
				boolean checkRequiredCourse = false;
				for (StudentHistory studentHistory : stuHistoryList) {
					if(prerequisites.getRequiredCourseId().equals(studentHistory.getCourseId())) {

						int studHistGrade = this.generateGradeIntValue(studentHistory.getGrade());
						int reqCourseGrade = this.generateGradeIntValue(prerequisites.getMinGrade());
						
						if(studHistGrade >= reqCourseGrade) {
							checkRequiredCourse = true;
							break;
						}

					}
				}
				
				if(prerequisitiess.size() == 1) {
					String enrolledCourseId = prerequisitiess.get(0).getRequiredCourseId();
					if(enrolledCourseId == null) {
						checkRequiredCourse = true;
					}
				}
				
				if(!checkRequiredCourse) {
					return "You need to enroll the prerequisite courses first, please check Major Requirement with minimun Grade.";
				}
			}
		//}
		
		
		return "ok";	
	}
	
	private int generateGradeIntValue(String grade) {
		
		if(grade.equalsIgnoreCase("a+")) {
			return 13;
		}
		else if(grade.equalsIgnoreCase("a")) {
			return 12;
		}
		else if(grade.equalsIgnoreCase("a-")) {
			return 11;
		}
		else if(grade.equalsIgnoreCase("b+")) {
			return 10;
		}
		else if(grade.equalsIgnoreCase("b")) {
			return 9;
		}
		else if(grade.equalsIgnoreCase("b-")) {
			return 8;
		}
		else if(grade.equalsIgnoreCase("c+")) {
			return 7;
		}
		else if(grade.equalsIgnoreCase("c")) {
			return 6;
		}
		else if(grade.equalsIgnoreCase("c-")) {
			return 5;
		}
		else if(grade.equalsIgnoreCase("d")) {
			return 4;
		}
		else if(grade.equalsIgnoreCase("e")) {
			return 3;
		}
		else if(grade.equalsIgnoreCase("f")) {
			return 2;
		}
		else if(grade.equalsIgnoreCase("w")) {
			return 1;
		}
		return 0;
	}
	
	private void cleanModel(CourseSection courseSection) {
		courseSection.setCourseSectionId(null);
		courseSection.setSectionNumber(null);
		courseSection.setCredit(null);
		courseSection.setSemesterYear(null);
		courseSection.setTimeWindow(null);
		courseSection.setFacultyFirstName(null);
		courseSection.setFacultyLastName(null);
		courseSection.setDay(null);
		courseSection.setPeriod(null);
		courseSection.setCourseName(null);
		courseSection.setDepartmentName(null);
	}
	
	private void refreshCourseSection(CourseSection courseSection) {
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		this.checkTimeWindow(courseSectionList);
		for (CourseSection courseSec : courseSectionList) {
			courseSec.setTimeWindow(edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter.changeDateFormatForTimeSlot(courseSec.getTimeWindow()));
		}
		courseSection.setCourseSectionList(courseSectionList);
	}
	
	
	private void checkTimeWindow(List<CourseSection> courseSections) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		try {
			Date currentDateTime = dateFormater.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")));
			
			for (CourseSection courseSection : courseSections) {
				Date timeWindow = dateFormater.parse(courseSection.getTimeWindow());
				courseSection.setCheckEnrollment(timeWindow.after(currentDateTime));
			}
		} catch (ParseException e) {
				e.printStackTrace();
		}
		
	}
}
