package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Student;

@Mapper
public interface StudentMapper {
	public void save(@Param("student") Student student);
	
	public List<Student> findStudent(@Param("student") Student student);
	
	public void update(@Param("student") Student student);
	
	public void delete(@Param("id") Integer id);
}
