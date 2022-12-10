package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.AttendanceDao;
import edu.chalkboarduni.uniregistrationsystem.model.Attendance;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	private AttendanceDao attendanceDao;

	@Override
	public void save(Attendance attendance) {
		attendanceDao.save(attendance);
	}

	@Override
	public List<Attendance> findAttendance(Attendance attendance) {
		return attendanceDao.findAttendance(attendance);
	}
	
	@Override
	public void delete(Attendance attendance) {
		attendanceDao.delete(attendance);
	}

	@Override
	public void update(Attendance attendance) {
		attendanceDao.update(attendance);
	}
}
