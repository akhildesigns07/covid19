package com.akhil.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akhil.project.model.CovidEntity;

@Repository
public interface CovidRepository extends CrudRepository<CovidEntity,Long> {

	CovidEntity findByloc(String loc);
	
	  @Modifying	  
	  @Transactional	  
	  @Query(value="UPDATE CovidEntity  SET confirmedCasesForeign=:#{#CovidEntity.confirmedCasesForeign},confirmedCasesIndian=:#{#CovidEntity.confirmedCasesIndian},discharged=:#{#CovidEntity.discharged},deaths=:#{#CovidEntity.deaths},totalConfirmed=:#{#CovidEntity.totalConfirmed} WHERE loc=:#{#CovidEntity.loc} and id =:#{#CovidEntity.id}",nativeQuery = true )
	  
	  int update(@Param("CovidEntity") CovidEntity CovidEntity);
	  //int update(@Param("confirmedCasesForeign") Integer confirmedCasesForeign,@Param("id") Integer id,@Param("loc") String loc);
	 
	 

}
