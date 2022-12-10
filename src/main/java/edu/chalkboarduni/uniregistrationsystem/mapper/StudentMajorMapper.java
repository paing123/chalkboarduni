package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.StudentMajor;

@Mapper
public interface StudentMajorMapper {
	public void save(@Param("studentMajor") StudentMajor studentMajor);
	
	public List<StudentMajor> findStudentMajor(@Param("studentMajor") StudentMajor studentMajor);
	
	public void update(@Param("studentMajor") StudentMajor studentMajor);
	
	public void delete(@Param("studentMajor") StudentMajor studentMajor);
}
