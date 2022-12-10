package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Attendance;

@Mapper
public interface AttendanceMapper {
	public void save(@Param("attendance") Attendance attendance);
	
	public List<Attendance> findAttendance(@Param("attendance") Attendance attendance);
	
	public void update(@Param("attendance") Attendance attendance);
	
	public void delete(@Param("attendance") Attendance attendance);
}
