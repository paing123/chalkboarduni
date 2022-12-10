package edu.chalkboarduni.uniregistrationsystem.model;


import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prerequisites {
	
	private Integer prereqId;
	
	private String requiredCourseId;
	
	private String requiredCourseName;
	
	@NotNull(message = "Course Id is required")
	private String courseId;
	
	private String courseName;
	
	private String minGrade;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Prerequisites> prerequisitesList;
	
	private List<Course> courseList;
}
