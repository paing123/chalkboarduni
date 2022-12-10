package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
	
	@NotNull(message = "Student is required")
	private Integer studentId;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	private Integer facultyId;
	
	private String facultyFirstName;
	
	private String facultyLastName;

	private String courseId;
	
	private String courseName;

	@NotNull(message = "Course Section is required")
	private Integer courseSectionId;
	
	private String semesterYear;
	
	@NotNull(message = "Date is required")
	private String date;
	
	@NotNull(message = "Absent/Present is required")
	private String absentPresent;
	
	private String errorMessage;
	
	private String successMessage;
	
	private List<Student> studentList;
	
	private List<Faculty> facultyList;
	
	private List<Course> courseList;
	
	private List<CourseSection> courseSectionList;
	
	private List<Attendance> attendanceList;
}
