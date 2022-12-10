package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Minor;

@Mapper
public interface MinorMapper {
	public void save(@Param("minor") Minor minor);
	
	public List<Minor> findMinor(@Param("minor") Minor Minor);
	
	public void update(@Param("minor") Minor Minor);
	
	public void delete(@Param("id") String id);
}
