package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.StudentHold;

@Mapper
public interface StudentHoldMapper {
	public void save(@Param("studentHold") StudentHold studentHold);
	
	public List<StudentHold> findStudentHold(@Param("studentHold") StudentHold studentHold);
	
	public void delete(@Param("studentHold") StudentHold studentHold);
}
