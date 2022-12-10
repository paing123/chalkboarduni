package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.CourseMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao {
	
	@Autowired
	private CourseMapper courseMapper;
	
	public void save(Course course) {
		courseMapper.save(course);
	}
	
	public List<Course> findCourse(Course course) {
		List<Course> courses = courseMapper.findCourse(course);
		return courses;
	}
	
	public void delete(String id) {
		courseMapper.delete(id);
	}
	
	public void update(Course course) {
		courseMapper.update(course);
	}
}
