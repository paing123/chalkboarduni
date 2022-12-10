package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.StudentHistoryMapper;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;

@Repository
public class StudentHistoryDaoImpl implements StudentHistoryDao {
	
	@Autowired
	private StudentHistoryMapper studentHistoryMapper;
	
	public void save(StudentHistory studentHistory) {
		studentHistoryMapper.save(studentHistory);
	}
	
	public List<StudentHistory> findStudentHistory(StudentHistory studentHistory) {
		List<StudentHistory> studentHistorys = studentHistoryMapper.findStudentHistory(studentHistory);
		return studentHistorys;
	}
	
	public void delete(Integer studentId, Integer courseSectionId) {
		studentHistoryMapper.delete(studentId, courseSectionId);
	}
	
	public void update(StudentHistory studentHistory) {
		studentHistoryMapper.update(studentHistory);
	}
}
