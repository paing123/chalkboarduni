package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.MinorRequirement;

@Mapper
public interface MinorRequirementMapper {
	public void save(@Param("minorRequirement") MinorRequirement minorRequirement);
	
	public List<MinorRequirement> findMinorRequirement(@Param("minorRequirement") MinorRequirement minorRequirement);
	
	public void delete(@Param("minorRequirement") MinorRequirement minorRequirement);
}
