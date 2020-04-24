package com.akhil.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.akhil.project.dto.covidHistory.CovidHistoryResponse;
import com.akhil.project.model.History;

public interface CovidHistoryRepository extends CrudRepository<History,Long>{

	List<History> findAllByloc(String loc);

	List<History> findBylocOrderByIdAsc(String loc);

}
