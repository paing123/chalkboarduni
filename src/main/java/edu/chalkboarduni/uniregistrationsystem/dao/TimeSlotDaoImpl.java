package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.TimeSlotMapper;
import edu.chalkboarduni.uniregistrationsystem.model.TimeSlot;

@Repository
public class TimeSlotDaoImpl implements TimeSlotDao {
	
	@Autowired
	private TimeSlotMapper timeSlotMapper;
	
	public void save(TimeSlot timeSlot) {
		timeSlotMapper.save(timeSlot);
	}
	
	public List<TimeSlot> findTimeSlot(TimeSlot timeSlot) {
		List<TimeSlot> timeSlots = timeSlotMapper.findTimeSlot(timeSlot);
		return timeSlots;
	}
	
	public void delete(Integer id) {
		timeSlotMapper.delete(id);
	}
	
	public void update(TimeSlot timeSlot) {
		timeSlotMapper.update(timeSlot);
	}
}
