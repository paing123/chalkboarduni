package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Course;

public interface CourseService {
	
	void save(Course course);

	List<Course> findCourse(Course course);
	
	void delete(String id);

	void update(Course course);
}
