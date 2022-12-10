package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.AdvisingMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Advising;

@Repository
public class AdvisingDaoImpl implements AdvisingDao {
	
	@Autowired
	private AdvisingMapper advisingMapper;
	
	public void save(Advising advising) {
		advisingMapper.save(advising);
	}
	
	public List<Advising> findAdvising(Advising advising) {
		List<Advising> advisings = advisingMapper.findAdvising(advising);
		return advisings;
	}
	
	public void delete(String id) {
		advisingMapper.delete(id);
	}
	
	public void update(Advising advising) {
		advisingMapper.update(advising);
	}
}
