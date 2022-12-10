package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinorRequirement {
	@NotNull(message = "MajorId is required")
	private String minorId;
	
	private String minorName;
	
	@NotNull(message = "CourseId is required")
	private String courseId;
	
	private String courseName;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<MinorRequirement> minorRequirementList;
	
	private List<Minor> minorList;
	
	private List<Course> courseList;
}
