package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Course;

@Mapper
public interface CourseMapper {
	public void save(@Param("course") Course course);
	
	public List<Course> findCourse(@Param("course") Course course);
	
	public void update(@Param("course") Course course);
	
	public void delete(@Param("id") String id);
}
