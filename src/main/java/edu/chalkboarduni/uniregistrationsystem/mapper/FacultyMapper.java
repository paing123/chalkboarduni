package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Faculty;

@Mapper
public interface FacultyMapper {
	public void save(@Param("faculty") Faculty faculty);
	
	public List<Faculty> findFaculty(@Param("faculty") Faculty faculty);
	
	public void update(@Param("faculty") Faculty faculty);
	
	public void delete(@Param("id") String id);
}
