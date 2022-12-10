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
public class Major {
	@NotNull(message = "MajorId is required")
	@Size(min = 2, max = 5, message = "MajorId length should be between 2 and 5")
	private String majorId;
	
	@NotNull(message = "DepartmentId is required")
	private String departmentId;
	
	private String departmentName;
	
	@NotNull(message = "Major Name is required")
	private String majorName;
	
	@NotNull(message = "Major Degree is required")
	private String majorDegree;
	
	@NotNull(message = "Credit to graduate is required")
	@Min(value = 1, message = "Credit to graduate shouldn't be less than 1")
	@Max(value = 999, message = "Credit to graduate shouldn't be greater than 999")
	private Integer creditsToGraduate;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Major> majorList;
	
	private List<Department> departmentList;
}
