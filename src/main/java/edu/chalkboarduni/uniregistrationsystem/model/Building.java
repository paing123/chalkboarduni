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
public class Building {
	@NotNull(message = "BuildingId is required")
	@Size(min = 2, max = 8, message = "BuildingId length should be between 2 and 8")
	private String buildingId;
	
	@NotNull(message = "Building Name is required")
	private String buildingName;
	
	@NotNull(message = "Building Location is required")
	private String buildingLocation;
	
	private String errorMessage;
	
	private String successMessage;
	
	private List<Building> buildingList;
}
