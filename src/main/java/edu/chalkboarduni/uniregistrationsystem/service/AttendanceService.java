package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Attendance;

public interface AttendanceService {
	
	void save(Attendance attendance);

	List<Attendance> findAttendance(Attendance attendance);
	
	void delete(Attendance attendance);

	void update(Attendance attendance);
}
