package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.MinorRequirement;

public interface MinorRequirementDao {
	 
	 void save(MinorRequirement minorRequirement);
	
	 List<MinorRequirement> findMinorRequirement(MinorRequirement minorRequirement);
	 
	 void delete(MinorRequirement minorRequirement);
}
