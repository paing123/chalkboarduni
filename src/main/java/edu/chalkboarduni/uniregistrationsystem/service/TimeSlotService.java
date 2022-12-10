package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.TimeSlot;

public interface TimeSlotService {
	
	void save(TimeSlot timeSlot);

	List<TimeSlot> findTimeSlot(TimeSlot timeSlot);
	
	void delete(Integer id);

	void update(TimeSlot timeSlot);
}
