package edu.chalkboarduni.uniregistrationsystem.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hold {
	
	@NotNull(message = "HoldId is required")
	@Size(min = 2, max = 10, message = "HoldId length should be between 2 and 10")
	private String holdId;
	
	@NotNull(message = "Hold Type is required")
	private String holdType;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Hold> holdList;
}
