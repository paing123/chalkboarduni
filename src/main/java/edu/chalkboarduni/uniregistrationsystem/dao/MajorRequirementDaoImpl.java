package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.MajorRequirementMapper;
import edu.chalkboarduni.uniregistrationsystem.model.MajorRequirement;

@Repository
public class MajorRequirementDaoImpl implements MajorRequirementDao {
	
	@Autowired
	private MajorRequirementMapper majorRequirementMapper;
	
	public void save(MajorRequirement majorRequirement) {
		majorRequirementMapper.save(majorRequirement);
	}
	
	public List<MajorRequirement> findMajorRequirement(MajorRequirement majorRequirement) {
		List<MajorRequirement> majorRequirements = majorRequirementMapper.findMajorRequirement(majorRequirement);
		return majorRequirements;
	}
	
	public void delete(MajorRequirement majorRequirement) {
		majorRequirementMapper.delete(majorRequirement);
	}	
}
