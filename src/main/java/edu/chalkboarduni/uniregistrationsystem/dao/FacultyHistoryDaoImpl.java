package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.FacultyHistoryMapper;
import edu.chalkboarduni.uniregistrationsystem.model.FacultyHistory;

@Repository
public class FacultyHistoryDaoImpl implements FacultyHistoryDao {
	
	@Autowired
	private FacultyHistoryMapper facultyHistoryMapper;
	
	public void save(FacultyHistory facultyHistory) {
		facultyHistoryMapper.save(facultyHistory);
	}
	
	public List<FacultyHistory> findFacultyHistory(FacultyHistory facultyHistory) {
		List<FacultyHistory> FacultyHistorys = facultyHistoryMapper.findFacultyHistory(facultyHistory);
		return FacultyHistorys;
	}
	
	public void delete(Integer facId,Integer courseSectionId) {
		facultyHistoryMapper.delete(facId,courseSectionId);
	}
	
	public void update(FacultyHistory facultyHistory) {
		facultyHistoryMapper.update(facultyHistory);
	}
}
