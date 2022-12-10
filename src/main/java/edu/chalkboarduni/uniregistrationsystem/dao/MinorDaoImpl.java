package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.MinorMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Minor;

@Repository
public class MinorDaoImpl implements MinorDao {
	
	@Autowired
	private MinorMapper minorMapper;
	
	public void save(Minor minor) {
		minorMapper.save(minor);
	}
	
	public List<Minor> findMinor(Minor minor) {
		List<Minor> minors = minorMapper.findMinor(minor);
		return minors;
	}
	
	public void delete(String id) {
		minorMapper.delete(id);
	}
	
	public void update(Minor minor) {
		minorMapper.update(minor);
	}
}
