package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.CourseDao;
import edu.chalkboarduni.uniregistrationsystem.model.Course;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;

	@Override
	public void save(Course course) {
		courseDao.save(course);
	}

	@Override
	public List<Course> findCourse(Course course) {
		return courseDao.findCourse(course);
	}
	
	@Override
	public void delete(String id) {
		courseDao.delete(id);
	}

	@Override
	public void update(Course course) {
		courseDao.update(course);
	}
}
