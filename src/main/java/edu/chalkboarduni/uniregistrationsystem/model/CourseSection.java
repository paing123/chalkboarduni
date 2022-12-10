package edu.chalkboarduni.uniregistrationsystem.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSection {
	@NotNull(message = "CourseSectionId is required")
	@Min(value = 1, message = "CourseSectionId shouldn't be less than 1")
	@Max(value = 999999999, message = "CourseSectionId shouldn't be greater than 999999999")
	private Integer courseSectionId;
	
	@NotNull(message = "Section Number is required")
	private String sectionNumber;
	
	@NotNull(message = "Available Seats are required")
	@Min(value = 1, message = "Available Seats shouldn't be less than 1")
	@Max(value = 999999999, message = "Available Seats shouldn't be greater than 999999999")
	private Integer availableSeats;
	
	private Integer remainingSeats;
	
	@NotNull(message = "Semester Year is required")
	private String semesterYear;
	
	@NotNull(message = "Time Window is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	private String timeWindow;
	
	@NotNull(message = "Faculty is required")
	private Integer facultyId;
	
	private String facultyFirstName;
	
	private String facultyLastName;
	
	@NotNull(message = "Time Slot is required")
	private Integer timeSlotId;
	
	private String day;
	
	private String period;
	
	@NotNull(message = "Room is required")
	private String roomId;
	
	private String roomType;
	
	@NotNull(message = "Course is required")
	private String courseId;
	
	private String courseName;
	
	private String departmentId;
	
	private String departmentName;
	
	private Integer credit;
	
	private Boolean checkEnrollment;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Faculty> facultyList;
	
	private List<TimeSlot> timeSlotList;
	
	private List<Room> roomList;
	
	private List<Course> courseList;
	
	private List<Department> departmentList;
	
	private List<CourseSection> courseSectionList;
}
