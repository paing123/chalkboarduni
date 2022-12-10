package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import edu.chalkboarduni.uniregistrationsystem.model.TimeSlot;

@Mapper
public interface TimeSlotMapper {
	public void save(@Param("timeSlot") TimeSlot timeSlot);
	
	public List<TimeSlot> findTimeSlot(@Param("timeSlot") TimeSlot timeSlot);
	
	public void update(@Param("timeSlot") TimeSlot timeSlot);
	
	public void delete(@Param("id") Integer id);
}
