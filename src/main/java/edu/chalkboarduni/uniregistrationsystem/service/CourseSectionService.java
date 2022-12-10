package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;

public interface CourseSectionService {
	
	void save(CourseSection courseSection);

	List<CourseSection> findCourseSection(CourseSection courseSection);
	
	void delete(Integer id);

	void update(CourseSection courseSection);
}
