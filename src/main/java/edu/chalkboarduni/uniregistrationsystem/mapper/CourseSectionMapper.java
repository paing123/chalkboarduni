package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;

@Mapper
public interface CourseSectionMapper {
	public void save(@Param("courseSection") CourseSection courseSection);
	
	public List<CourseSection> findCourseSection(@Param("courseSection") CourseSection courseSection);
	
	public void update(@Param("courseSection") CourseSection courseSection);
	
	public void delete(@Param("id") Integer id);
}
