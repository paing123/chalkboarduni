package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;

@Mapper
public interface EnrollmentMapper {
	public void save(@Param("enrollment") Enrollment enrollment);
	
	public List<Enrollment> findEnrollment(@Param("enrollment") Enrollment enrollment);
	
	public void update(@Param("enrollment") Enrollment enrollment);
	
	public void delete(@Param("enrollment") Enrollment enrollment);
}
