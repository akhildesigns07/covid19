package com.akhil.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.akhil.project.dto.covidHistory.CovidHistoryResponse;
import com.akhil.project.model.CovidEntity;
import com.akhil.project.model.History;

public interface CovidHistoryRepository extends CrudRepository<History,Long>{

	List<History> findAllByloc(String loc);
	List<History> findAllByupdatedDate(Date date);

	List<History> findTop10BylocOrderByIdDesc(String loc);
	
	//List<Date> findDistinctupdatedDate;
	
	@Query(value="SELECT DISTINCT UPDATEDDATE FROM HISTORY",nativeQuery = true )
	  
	  List<Date> select();

}
