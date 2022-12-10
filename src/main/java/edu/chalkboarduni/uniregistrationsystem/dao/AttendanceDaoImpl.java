package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.AttendanceMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Attendance;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
	
	@Autowired
	private AttendanceMapper attendanceMapper;
	
	public void save(Attendance attendance) {
		attendanceMapper.save(attendance);
	}
	
	public List<Attendance> findAttendance(Attendance attendance) {
		List<Attendance> attendances = attendanceMapper.findAttendance(attendance);
		return attendances;
	}
	
	public void delete(Attendance attendance) {
		attendanceMapper.delete(attendance);
	}
	
	public void update(Attendance attendance) {
		attendanceMapper.update(attendance);
	}
}
