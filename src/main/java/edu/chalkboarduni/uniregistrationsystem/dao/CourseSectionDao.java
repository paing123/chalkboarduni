package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;

public interface CourseSectionDao {
	 
	 void save(CourseSection courseSection);
	
	 List<CourseSection> findCourseSection(CourseSection courseSection);
	 
	 void delete(Integer id);
	 
	 void update(CourseSection courseSection);
}
