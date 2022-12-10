package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.FacultyMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;

@Repository
public class FacultyDaoImpl implements FacultyDao {
	
	@Autowired
	private FacultyMapper facultyMapper;
	
	public void save(Faculty faculty) {
		facultyMapper.save(faculty);
	}
	
	public List<Faculty> findFaculty(Faculty faculty) {
		List<Faculty> facultys = facultyMapper.findFaculty(faculty);
		return facultys;
	}
	
	public void delete(String id) {
		facultyMapper.delete(id);
	}
	
	public void update(Faculty faculty) {
		facultyMapper.update(faculty);
	}
}
