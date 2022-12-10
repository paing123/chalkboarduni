package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
	
	@NotNull(message = "Student is required")
	private Integer studentId;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	@NotNull(message = "CourseSection is required")
	private Integer courseSectionId;
	
	private String sectionNumber;
	
	private Integer availableSeats;
	
	private String semesterYear;
	
	private Integer timeSlotId;
	
	@DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	private String timeWindow;
	
	private String courseId;
	
	private String courseName;
	
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private String enrollmentDate;
	
	private String disabledStatus;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Student> studentList;
	
	private List<Course> courseList;
	
	private List<CourseSection> courseSectionList;
	
	private List<Enrollment> enrollmentList;
}
