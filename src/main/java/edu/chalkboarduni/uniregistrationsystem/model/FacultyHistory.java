package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyHistory {
	
	@NotNull(message = "Faculty is required")
	private Integer facultyId;
	
	private String facultyFirstName;
	
	private String facultyLastName;
	
	private String facultyType;
	
	private String rank;
	
	@NotNull(message = "CourseSection is required")
	private String courseSectionId;
	
	private String semesterYear;
	
	private String courseId;
	
	private String courseName;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Course> courseList;
	
	private List<CourseSection> courseSectionList;
	
	private List<Faculty> facultyList;
	
	private List<FacultyHistory> facultyHistoryList;
}
