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
public class Minor {
	
	@NotNull(message = "Minor is required")
	@Size(min = 2, max = 5, message = "Minor length should be between 2 and 5")
	private String minorId;
	
	@NotNull(message = "DepartmentId is required")
	private String departmentId;
	
	private String departmentName;
	
	@NotNull(message = "Minor Name is required")
	private String minorName;
	
	@NotNull(message = "Required Credit is required")
	@Min(value = 1, message = "Required Credit shouldn't be less than 1")
	@Max(value = 99, message = "Required Credit shouldn't be greater than 99")
	private Integer requiredCredits;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Minor> minorList;
	
	private List<Department> departmentList;
}
