package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.MinorRequirementMapper;
import edu.chalkboarduni.uniregistrationsystem.model.MinorRequirement;

@Repository
public class MinorRequirementDaoImpl implements MinorRequirementDao {
	
	@Autowired
	private MinorRequirementMapper minorRequirementMapper;
	
	public void save(MinorRequirement minorRequirement) {
		minorRequirementMapper.save(minorRequirement);
	}
	
	public List<MinorRequirement> findMinorRequirement(MinorRequirement minorRequirement) {
		List<MinorRequirement> MinorRequirements = minorRequirementMapper.findMinorRequirement(minorRequirement);
		return MinorRequirements;
	}
	
	public void delete(MinorRequirement minorRequirement) {
		minorRequirementMapper.delete(minorRequirement);
	}	
}
