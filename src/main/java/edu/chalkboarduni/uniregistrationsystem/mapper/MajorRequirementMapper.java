package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.MajorRequirement;

@Mapper
public interface MajorRequirementMapper {
	public void save(@Param("majorRequirement") MajorRequirement majorRequirement);
	
	public List<MajorRequirement> findMajorRequirement(@Param("majorRequirement") MajorRequirement majorRequirement);
	
	public void delete(@Param("majorRequirement") MajorRequirement majorRequirement);
}
