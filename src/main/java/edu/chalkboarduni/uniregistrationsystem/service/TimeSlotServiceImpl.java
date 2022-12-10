package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.TimeSlotDao;
import edu.chalkboarduni.uniregistrationsystem.model.TimeSlot;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
	
	@Autowired
	private TimeSlotDao timeSlotDao;

	@Override
	public void save(TimeSlot timeSlot) {
		timeSlotDao.save(timeSlot);
	}

	@Override
	public List<TimeSlot> findTimeSlot(TimeSlot timeSlot) {
		return timeSlotDao.findTimeSlot(timeSlot);
	}
	
	@Override
	public void delete(Integer id) {
		timeSlotDao.delete(id);
	}

	@Override
	public void update(TimeSlot timeSlot) {
		timeSlotDao.update(timeSlot);
	}
}
