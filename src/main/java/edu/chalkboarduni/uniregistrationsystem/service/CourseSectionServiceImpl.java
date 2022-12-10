package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.CourseSectionDao;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;

@Service
public class CourseSectionServiceImpl implements CourseSectionService {
	
	@Autowired
	private CourseSectionDao courseSectionDao;

	@Override
	public void save(CourseSection courseSection) {
		courseSectionDao.save(courseSection);
	}

	@Override
	public List<CourseSection> findCourseSection(CourseSection courseSection) {
		return courseSectionDao.findCourseSection(courseSection);
	}
	
	@Override
	public void delete(Integer id) {
		courseSectionDao.delete(id);
	}

	@Override
	public void update(CourseSection courseSection) {
		courseSectionDao.update(courseSection);
	}
}
