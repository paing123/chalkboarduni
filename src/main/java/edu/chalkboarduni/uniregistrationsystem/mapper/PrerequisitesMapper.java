package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import edu.chalkboarduni.uniregistrationsystem.model.Prerequisites;

@Mapper
public interface PrerequisitesMapper {
	public void save(@Param("prerequisites") Prerequisites prerequisites);
	
	public List<Prerequisites> findPrerequisites(@Param("prerequisites") Prerequisites prerequisites);
	
	public void update(@Param("prerequisites") Prerequisites prerequisites);
	
	public void delete(@Param("id") Integer id);
}
