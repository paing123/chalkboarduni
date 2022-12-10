package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.MajorMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Major;

@Repository
public class MajorDaoImpl implements MajorDao {
	
	@Autowired
	private MajorMapper majorMapper;
	
	public void save(Major major) {
		majorMapper.save(major);
	}
	
	public List<Major> findMajor(Major major) {
		List<Major> majors = majorMapper.findMajor(major);
		return majors;
	}
	
	public void delete(String id) {
		majorMapper.delete(id);
	}
	
	public void update(Major major) {
		majorMapper.update(major);
	}
}
