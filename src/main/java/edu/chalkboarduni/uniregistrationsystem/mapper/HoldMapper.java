package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Hold;

@Mapper
public interface HoldMapper {
	public void save(@Param("hold") Hold hold);
	
	public List<Hold> findHold(@Param("hold") Hold hold);
	
	public void update(@Param("hold") Hold hold);
	
	public void delete(@Param("id") String id);
}
