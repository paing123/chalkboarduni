package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	@NotNull(message = "DepartmentId is required")
	@Size(min = 2, max = 5, message = "DepartmentId length should be between 2 and 5")
	private String departmentId;
	
	@NotNull(message = "Department Name is required")
	private String departmentName;
	
	@NotNull(message = "Email is required")
	@Email(message = "Your email is not valid format!")
	private String email;
	
	@NotNull(message = "Phone Num is required")
	private String phoneNum;
	
	@NotNull(message = "Build room is required")
	private String buildingRoom;
	
	@NotNull(message = "Department Chair is required")
	private String departmentChair;
	
	@NotNull(message = "Department Secretary is required")
	private String departmentSecretary;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Department> departmentList;
}
