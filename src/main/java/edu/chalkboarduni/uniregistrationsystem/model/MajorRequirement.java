package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorRequirement {
	
	@NotNull(message = "MajorId is required")
	private String majorId;
	
	private String majorName;
	
	@NotNull(message = "CourseId is required")
	private String courseId;
	
	private String courseName;
	
	private String status;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<MajorRequirement> majorRequirementList;
	
	private List<Major> majorList;
	
	private List<Course> courseList;
}
