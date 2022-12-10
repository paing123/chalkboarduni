package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	
	@NotNull(message = "CourseId is required")
	@Size(min = 2, max = 10, message = "CourseId length should be between 2 and 10")
	private String courseId;
	
	@NotNull(message = "DepartmentId is required")
	private String departmentId;
	
	private String departmentName;
	
	@NotNull(message = "Course Name is required")
	private String courseName;
	
	@NotNull(message = "Course Type is required")
	private String courseType;
	
	@NotNull(message = "Credit is required")
	@Min(value = 1, message = "Credit shouldn't be less than 1")
	@Max(value = 999999999, message = "Credit shouldn't be greater than 999999999")
	private Integer credit;
	
	private String errorMessage;	
	
	private String successMessage;
	
	private List<Course> courseList;
	
	private List<Department> departmentList;
}
