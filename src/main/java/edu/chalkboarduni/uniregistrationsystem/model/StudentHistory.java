package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentHistory {
	
	@NotNull(message = "StudentId is required")
	private Integer studentId;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	private String studentType;
	
	private String studentProgram;
	
	private String academicYear;
	
	private String creditsCompleted;
	
	private String studentStatus;
	
	@NotNull(message = "CourseSectionId is required")
	private Integer courseSectionId;
	
	private Integer timeSlotId;
	
	private String semesterYear;
	
	private String courseId;
	
	private String courseName;
	
	private Integer facultyId;
	
	@NotNull(message = "Grade is required")
	private String grade;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Course> courseList;
	
	private List<CourseSection> courseSectionList;
	
	private List<Student> studentList;
	
	private List<StudentHistory> studentHistoryList;
}
