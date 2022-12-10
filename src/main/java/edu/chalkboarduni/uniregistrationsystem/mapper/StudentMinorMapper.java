package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.StudentMinor;

@Mapper
public interface StudentMinorMapper {
	public void save(@Param("studentMinor") StudentMinor studentMinor);
	
	public List<StudentMinor> findStudentMinor(@Param("studentMinor") StudentMinor studentMinor);
	
	public void update(@Param("studentMinor") StudentMinor studentMinor);
	
	public void delete(@Param("studentMinor") StudentMinor studentMinor);
}
