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
import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.model.Room;
import edu.chalkboarduni.uniregistrationsystem.model.TimeSlot;
import edu.chalkboarduni.uniregistrationsystem.service.CourseSectionService;
import edu.chalkboarduni.uniregistrationsystem.service.CourseService;
import edu.chalkboarduni.uniregistrationsystem.service.DepartmentService;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;
import edu.chalkboarduni.uniregistrationsystem.service.RoomService;
import edu.chalkboarduni.uniregistrationsystem.service.TimeSlotService;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;

@Controller
public class CourseSectionController {

	@Autowired
	private CourseSectionService courseSectionService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(CourseSection CourseSection, Model model) {
        model.addAttribute("model", CourseSection);
    }
	
	@RequestMapping(value = {"/admin/coursesections"})
	public String getCourseSectionListForAdmin(CourseSection courseSection) {
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		List<Department> departmentList = departmentService.findDepartment(new Department());

		courseSection.setCourseList(courseList);
		courseSection.setFacultyList(facultyList);
		courseSection.setTimeSlotList(timeSlotList);
		courseSection.setRoomList(roomList);
		courseSection.setDepartmentList(departmentList);
		return "admin/coursesections";
	}
	
	@RequestMapping(value = {"/admin/searchcoursesections"})
	public String searchCourseSectionList(CourseSection courseSection) {
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		List<Department> departmentList = departmentService.findDepartment(new Department());
		
		for (CourseSection courseSec : courseSectionList) {
			courseSec.setTimeWindow(DateTimeFormatter.changeDateFormatForTimeSlot(courseSec.getTimeWindow()));
		}
		
		courseSection.setCourseSectionList(courseSectionList);
		courseSection.setCourseList(courseList);
		courseSection.setFacultyList(facultyList);
		courseSection.setTimeSlotList(timeSlotList);
		courseSection.setRoomList(roomList);
		courseSection.setDepartmentList(departmentList);
		return "admin/coursesections";
	}
	
	@GetMapping(value = {"/admin/registercoursesection"})
	public String getCourseSectionForm(CourseSection courseSection,Model model) {
		courseSection = new CourseSection();
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		courseSection.setCourseList(courseList);
		courseSection.setFacultyList(facultyList);
		courseSection.setTimeSlotList(timeSlotList);
		courseSection.setRoomList(roomList);
		model.addAttribute("model",courseSection);
		return "admin/registercoursesection";
	}
	
	@PostMapping(value = {"/admin/registercoursesection"})
	public String registerCourseSection(@Valid @ModelAttribute("model") CourseSection courseSection, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Course> courseList = courseService.findCourse(new Course());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
			List<Room> roomList = roomService.findRoom(new Room());
			courseSection.setCourseList(courseList);
			courseSection.setFacultyList(facultyList);
			courseSection.setTimeSlotList(timeSlotList);
			courseSection.setRoomList(roomList);
			return "admin/registercoursesection";
		}
		
		if("error".equals(this.checkTimeSlotForSameRoom(courseSection, "register"))) {
			List<Course> courseList = courseService.findCourse(new Course());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
			List<Room> roomList = roomService.findRoom(new Room());
			courseSection.setCourseList(courseList);
			courseSection.setFacultyList(facultyList);
			courseSection.setTimeSlotList(timeSlotList);
			courseSection.setRoomList(roomList);
			courseSection.setErrorMessage("Room occupied. Please choose a different room or time slot.");
			return "admin/registercoursesection";
		}
		
		
		try {
			courseSectionService.save(courseSection);
			return "redirect:/admin/searchcoursesections?successMessage=registered";
		} catch (Exception e) {
			List<Course> courseList = courseService.findCourse(new Course());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
			List<Room> roomList = roomService.findRoom(new Room());
			courseSection.setCourseList(courseList);
			courseSection.setFacultyList(facultyList);
			courseSection.setTimeSlotList(timeSlotList);
			courseSection.setRoomList(roomList);
			courseSection.setErrorMessage("Duplication Error! Please check CourseSectionId.");
			return "admin/registercoursesection";
		}
	}
	
	@GetMapping("/admin/updatecoursesection/{id}")
	public String getExistCourseSection(@PathVariable("id") Integer courseSectionId,Model model) {
		CourseSection existCourseSection = new CourseSection();
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		existCourseSection.setCourseSectionId(courseSectionId);
		existCourseSection = courseSectionService.findCourseSection(existCourseSection).get(0);
		existCourseSection.setCourseList(courseList);
		existCourseSection.setFacultyList(facultyList);
		existCourseSection.setTimeSlotList(timeSlotList);
		existCourseSection.setRoomList(roomList);
		model.addAttribute("model",existCourseSection);
		return "admin/updatecoursesection";
	}
	
	@PostMapping("/admin/updatecoursesection")
	public String updateCourseSection(@Valid @ModelAttribute("model") CourseSection courseSection, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Course> courseList = courseService.findCourse(new Course());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
			List<Room> roomList = roomService.findRoom(new Room());
			courseSection.setCourseList(courseList);
			courseSection.setFacultyList(facultyList);
			courseSection.setTimeSlotList(timeSlotList);
			courseSection.setRoomList(roomList);
			return "admin/updatecoursesection";
		}
		
		if("error".equals(this.checkTimeSlotForSameRoom(courseSection, "update"))) {
			List<Course> courseList = courseService.findCourse(new Course());
			List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
			List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
			List<Room> roomList = roomService.findRoom(new Room());
			courseSection.setCourseList(courseList);
			courseSection.setFacultyList(facultyList);
			courseSection.setTimeSlotList(timeSlotList);
			courseSection.setRoomList(roomList);
			courseSection.setErrorMessage("Room occupied. Please choose a different room or time slot.");
			return "admin/updatecoursesection";
		}
		
		courseSectionService.update(courseSection);
		return "redirect:/admin/searchcoursesections?successMessage=updated";
	}
	
	@GetMapping("/admin/deletecoursesection/{id}")
	public String deleteCourseSection(@PathVariable("id") Integer courseSectionId) {
		courseSectionService.delete(courseSectionId);
		return "redirect:/admin/searchcoursesections?successMessage=deleted";
	}
	
	@RequestMapping(value = {"/faculty/coursesections"})
	public String getCourseSectionListForFaculty(CourseSection courseSection) {
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		List<Department> departmentList = departmentService.findDepartment(new Department());

		courseSection.setCourseList(courseList);
		courseSection.setFacultyList(facultyList);
		courseSection.setTimeSlotList(timeSlotList);
		courseSection.setRoomList(roomList);
		courseSection.setDepartmentList(departmentList);
		return "faculty/coursesections";
	}
	
	@RequestMapping(value = {"/faculty/searchcoursesections"})
	public String searchCourseSectionListForFaculty(CourseSection courseSection) {
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		List<Department> departmentList = departmentService.findDepartment(new Department());
		
		for (CourseSection courseSec : courseSectionList) {
			courseSec.setTimeWindow(DateTimeFormatter.changeDateFormatForTimeSlot(courseSec.getTimeWindow()));
		}
		
		courseSection.setCourseSectionList(courseSectionList);
		courseSection.setCourseList(courseList);
		courseSection.setFacultyList(facultyList);
		courseSection.setTimeSlotList(timeSlotList);
		courseSection.setRoomList(roomList);
		courseSection.setDepartmentList(departmentList);
		return "faculty/coursesections";
	}
	
	@RequestMapping(value = {"/researcherstaff/coursesections"})
	public String getCourseSectionListForResearcherStaff(CourseSection courseSection) {
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		List<Department> departmentList = departmentService.findDepartment(new Department());

		courseSection.setCourseList(courseList);
		courseSection.setFacultyList(facultyList);
		courseSection.setTimeSlotList(timeSlotList);
		courseSection.setRoomList(roomList);
		courseSection.setDepartmentList(departmentList);
		return "researcherstaff/coursesections";
	}
	
	@RequestMapping(value = {"/researcherstaff/searchcoursesections"})
	public String searchCourseSectionListForResearcherstaff(CourseSection courseSection) {
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(courseSection);
		List<Course> courseList = courseService.findCourse(new Course());
		List<Faculty> facultyList = facultyService.findFaculty(new Faculty());
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(new TimeSlot());
		List<Room> roomList = roomService.findRoom(new Room());
		List<Department> departmentList = departmentService.findDepartment(new Department());
		
		for (CourseSection courseSec : courseSectionList) {
			courseSec.setTimeWindow(DateTimeFormatter.changeDateFormatForTimeSlot(courseSec.getTimeWindow()));
		}
		
		courseSection.setCourseSectionList(courseSectionList);
		courseSection.setCourseList(courseList);
		courseSection.setFacultyList(facultyList);
		courseSection.setTimeSlotList(timeSlotList);
		courseSection.setRoomList(roomList);
		courseSection.setDepartmentList(departmentList);
		return "researcherstaff/coursesections";
	}
	
	private String checkTimeSlotForSameRoom(CourseSection newCourseSection, String checkForm) {
		
		List<CourseSection> courseSectionList = courseSectionService.findCourseSection(new CourseSection());
		
		int count = 0;
		boolean checkSameCoureSectionId = false;
		
		for (CourseSection courseSection : courseSectionList) {
			if(courseSection.getRoomId().equals(newCourseSection.getRoomId()) && courseSection.getTimeSlotId().equals(newCourseSection.getTimeSlotId())){
				
				if(courseSection.getCourseSectionId().equals(newCourseSection.getCourseSectionId())) {
					checkSameCoureSectionId = true;
				}
				count++;
			}
		}
		
		if("register".equals(checkForm)) {
			//new course section can't be same room with same timeslot
			if(count == 0) {
				return "ok";
			}
			return "error";
		}else {
			//existing course section can be same room with same timeslot for 1 time, coz that already existed in db
			if(checkSameCoureSectionId) {
				if(count == 1) { //same itself
					return "ok";
				}
				return "error";
			}
			else {
				if(count == 1) //not same itself
				{
					return "error";
				}
				else 
				{
					return "ok";
				}
			}
		}
	}
}
