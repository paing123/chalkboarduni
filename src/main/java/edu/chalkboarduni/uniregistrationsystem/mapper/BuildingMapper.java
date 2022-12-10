package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Building;

@Mapper
public interface BuildingMapper {
	public void save(@Param("building") Building building);
	
	public List<Building> findBuilding(@Param("building") Building building);
	
	public void update(@Param("building") Building building);
	
	public void delete(@Param("id") String id);
}
