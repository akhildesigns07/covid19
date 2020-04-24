package com.akhil.project.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.akhil.project.dto.CovidLatestResponse;
import com.akhil.project.dto.Data;
import com.akhil.project.dto.Regional;
import com.akhil.project.dto.covidHistory.DataHistory;
import com.akhil.project.model.CovidEntity;
import com.akhil.project.model.History;
import com.akhil.project.repository.CovidRepository;

@Component
public class Mapper {
	private CovidRepository CovidRepository;
	
	
	  public Mapper(CovidRepository CovidRepository) { this.CovidRepository =
	  CovidRepository; }
	 
	public List<CovidEntity> covertCovidToRegional(List<Regional> region) {
		
		List<CovidEntity> covidEntities = new ArrayList<>();
		for(Regional r : region) {
			CovidEntity covidEntity = new CovidEntity();
			extracted(covidEntity,r);
			covidEntities.add(covidEntity);
		}
		
		return covidEntities;
		
	}
	private void extracted(CovidEntity covidEntity, Regional m) {
		covidEntity.setConfirmedCasesForeign(m.getConfirmedCasesForeign());
		covidEntity.setConfirmedCasesIndian(m.getConfirmedCasesIndian());
		covidEntity.setDeaths(m.getDeaths());
		covidEntity.setDischarged(m.getDischarged());
		covidEntity.setLoc(m.getLoc());
		covidEntity.setTotalConfirmed(m.getTotalConfirmed());
		
		
	}

	public List<History> convertHistory(List<DataHistory> data) throws ParseException {
		List<History> hist = new ArrayList<History>();
		for(DataHistory d : data) {
					
			  for(com.akhil.project.dto.covidHistory.Regional r : d.getRegional()) {
				  History histEntity = new History();	
				  histEntity.setUpdatedDate(Util.covertStringToDate(d.getDay()));
				  mapHistory(histEntity,r);
				  hist.add(histEntity);
			  }			 
			
		}
		return hist;
	}

	private void mapHistory(History histEntity, com.akhil.project.dto.covidHistory.Regional r) {
		histEntity.setConfirmedCasesForeign(r.getConfirmedCasesForeign());
		histEntity.setConfirmedCasesIndian(r.getConfirmedCasesIndian());
		histEntity.setDeaths(r.getDeaths());
		histEntity.setDischarged(r.getDischarged());
		histEntity.setLoc(r.getLoc());
		histEntity.setTotalConfirmed(r.getTotalConfirmed());
		
	}
	
}
