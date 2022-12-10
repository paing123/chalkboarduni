package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.CourseSectionMapper;
import edu.chalkboarduni.uniregistrationsystem.model.CourseSection;

@Repository
public class CourseSectionDaoImpl implements CourseSectionDao {
	
	@Autowired
	private CourseSectionMapper courseSectionMapper;
	
	public void save(CourseSection courseSection) {
		courseSectionMapper.save(courseSection);
	}
	
	public List<CourseSection> findCourseSection(CourseSection courseSection) {
		List<CourseSection> courseSections = courseSectionMapper.findCourseSection(courseSection);
		return courseSections;
	}
	
	public void delete(Integer id) {
		courseSectionMapper.delete(id);
	}
	
	public void update(CourseSection courseSection) {
		courseSectionMapper.update(courseSection);
	}
}
