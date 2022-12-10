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
public class Room {
	@NotNull(message = "RoomId is required")
	@Size(min = 2, max = 10, message = "RoomId length should be between 2 and 10")
	private String roomId;
	
	@NotNull(message = "BuildingId is required")
	private String buildingId;
	
	private String buildingName;
	
	@NotNull(message = "Room Capacity is required")
	@Min(value = 1, message = "Room Capacity should not be less than 1")
	@Max(value = 999999999, message = "Room Capacity should not be greater than 999999999")
	private Integer roomCapacity;
	
	@NotNull(message = "Room Type is required")
	private String roomType;
	
	private String successMessage;
	
	private String errorMessage;
	
	private List<Room> roomList;
	
	private List<Building> buildingList;
}
