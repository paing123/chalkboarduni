package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Major;

@Mapper
public interface MajorMapper {
	public void save(@Param("major") Major major);
	
	public List<Major> findMajor(@Param("major") Major major);
	
	public void update(@Param("major") Major major);
	
	public void delete(@Param("id") String id);
}
