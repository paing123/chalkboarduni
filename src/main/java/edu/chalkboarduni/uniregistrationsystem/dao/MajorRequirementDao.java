package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.MajorRequirement;

public interface MajorRequirementDao {
	 
	 void save(MajorRequirement majorRequirement);
	
	 List<MajorRequirement> findMajorRequirement(MajorRequirement majorRequirement);
	 
	 void delete(MajorRequirement majorRequirement);
}
