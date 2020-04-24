package com.akhil.project.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akhil.project.dto.CovidLatestResponse;
import com.akhil.project.dto.Regional;
import com.akhil.project.dto.covidHistory.CovidHistoryResponse;
import com.akhil.project.model.CovidEntity;
import com.akhil.project.model.History;
import com.akhil.project.model.User;
import com.akhil.project.repository.CovidHistoryRepository;
import com.akhil.project.repository.CovidRepository;
import com.akhil.project.util.Mapper;
import com.akhil.project.util.Util;
import com.akhil.project.webClient.WebClient;

@Controller
@RequestMapping("/")
public class Covid19LatestController {
	@Autowired
	WebClient webClient;
	@Autowired
	Mapper mapper;
	@Autowired
	CovidRepository covidRepository;
	
	@Autowired
	CovidHistoryRepository covidHistoryRepository;
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/{loc}")
	public String welcome(@PathVariable("loc") String loc,ModelMap model) {
	 
		List<History> response = covidHistoryRepository.findTop10BylocOrderByIdDesc(loc);
		//CovidEntity latestResponse = covidRepository.findByloc(loc);
		response.sort(Comparator.comparing(History::getId));
		response.forEach(m->{
			try {
				m.setUpdatedDate(Util.covertStringToDate(m.getUpdatedDate().toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//Map<Object,Object> map = null;
		List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
		List<List<Map<Object,Object>>> list2 = new ArrayList<List<Map<Object,Object>>>();
		List<List<Map<Object,Object>>> list3 = new ArrayList<List<Map<Object,Object>>>();
		List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> dataPoints2 = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> dataPoints3 = new ArrayList<Map<Object,Object>>();
		Integer index=0;
		for(History h : response) {
			Map<Object,Object> map = null;	
			Map<Object,Object> mapDeath = null;		
			Map<Object,Object> mapConfirm = null;	
			if(index < response.size()-1) {
				Integer deathDiff = 0,dayWise=0,totalConfi = 0;
				deathDiff = (response.get(index+1).getDeaths() - response.get(index).getDeaths());
				dayWise = (response.get(index+1).getConfirmedCasesIndian() - response.get(index).getConfirmedCasesIndian());
				totalConfi = (response.get(index+1).getTotalConfirmed() - response.get(index).getTotalConfirmed());
				map = new HashMap<Object,Object>(); map.put("label", h.getUpdatedDate()); map.put("y", dayWise);dataPoints1.add(map);
				mapDeath = new HashMap<Object,Object>(); mapDeath.put("label", h.getUpdatedDate()); mapDeath.put("y", deathDiff);dataPoints2.add(mapDeath);
				
			}
			mapConfirm = new HashMap<Object,Object>(); mapConfirm.put("label", h.getUpdatedDate()); mapConfirm.put("y", h.getTotalConfirmed());dataPoints3.add(mapConfirm);
			index++;
		}	
		list.add(dataPoints1);
		list2.add(dataPoints2);	
		list3.add(dataPoints3);	
		model.put("dataPointsList", list);
		model.put("dataPointsList2", list2);
		model.put("dataPointsList3", list3);
		System.out.println(dataPoints2);
		return "covidHistory";
	}
	
	@GetMapping("/all")
	public List<CovidEntity> getLatestCovidReport() throws IOException, URISyntaxException{
		List<CovidEntity> e = (List<CovidEntity>) covidRepository.findAll();
		
		return e;
		
}
	@SuppressWarnings("unchecked")
	@GetMapping("/history")
	public String getHistoryCovidReport(Model model) throws IOException, URISyntaxException{
		CovidHistoryResponse response = webClient.getHistoryValue();
		((HashMap<String, Object>) model).put("history",response.getData());
		return "covidHistory";
		
}
	@GetMapping("/saveHistory")
	public String getSaveHistoryCovidReport(Model model) throws IOException, URISyntaxException, ParseException{
		CovidHistoryResponse response = webClient.getHistoryValue();
		List<History> hist = mapper.convertHistory(response.getData());
		hist.forEach(m->covidHistoryRepository.save(m));
		return "covidHistory";
		
}
	@GetMapping("/")
	public String getLatestCovidReportView(ModelMap model) throws IOException, URISyntaxException{
		List<CovidEntity> e = (List<CovidEntity>) covidRepository.findAll();
		e.sort(Comparator.comparing(CovidEntity::getTotalConfirmed).reversed());
		model.put("lists",e);
		return "covidLatest";
		
}
	
	@PostMapping("/save")
	public String save() throws IOException, URISyntaxException {
		CovidLatestResponse response = webClient.getLatestValue();
		List<CovidEntity> covidEntities = mapper.covertCovidToRegional(response.getData().getRegional());
		covidEntities.forEach(m->covidRepository.save(m));
		return "done";
	}

	
	  @Scheduled(cron = "0 * * ? * *") public void saveCrone() throws IOException,
	  URISyntaxException { 
		//CovidLatestResponse region = new  CovidLatestResponse(); 
		  CovidLatestResponse response =  webClient.getLatestValue();
		  List<CovidEntity> covidEntities =	  mapper.covertCovidToRegional(response.getData().getRegional());
	  covidEntities.forEach(m->compareandsave(m));
	  System.out.println("Current time is :: " + Calendar.getInstance().getTime());
	  }
	 
	private Object compareandsave(CovidEntity m) {
		CovidEntity covidEntitiesDB = covidRepository.findByloc(m.getLoc());
		if(null==covidEntitiesDB) {
			covidRepository.save(m);
		}
		else {
		m.setId(covidEntitiesDB.getId());
		if((!covidEntitiesDB.getConfirmedCasesForeign().equals(m.getConfirmedCasesForeign())) ||
				(!covidEntitiesDB.getConfirmedCasesIndian().equals(m.getConfirmedCasesIndian())) ||
				(!covidEntitiesDB.getDeaths().equals(m.getDeaths())) ||
				(!covidEntitiesDB.getDischarged().equals(m.getDischarged())) ||
				(!covidEntitiesDB.getTotalConfirmed().equals(m.getTotalConfirmed()))) {			
			covidRepository.update(m);
			System.out.println(m.getLoc());
		}
		}
		
		return null;
	}
		
	@GetMapping("/date={date}")
	public String getHistoryCovidReportByDate(@PathVariable("date") String date,ModelMap model) throws IOException, URISyntaxException, ParseException{
		Date d = Util.covertStringToDate(date);
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(d);
		cal.add(Calendar.DATE,-1);
		Date previousDate = cal.getTime();
		List<History> confData = covidHistoryRepository.findAllByupdatedDate(d);
		List<History> previousData = covidHistoryRepository.findAllByupdatedDate(previousDate);
		confData.stream().forEach(m->{
			previousData.stream().forEach(k->{
				compareList(m,k);
			});
		});
		
		List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
		List<List<Map<Object,Object>>> list2 = new ArrayList<List<Map<Object,Object>>>();
		List<List<Map<Object,Object>>> list3 = new ArrayList<List<Map<Object,Object>>>();
		List<Map<Object,Object>> dataPoints2 = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> dataPoints3 = new ArrayList<Map<Object,Object>>();

		for(History conf : confData) {
			Map<Object,Object> map = null;	
			Map<Object,Object> mapTotal = null;	
			Map<Object,Object> mapDischarge = null;	
			map = new HashMap<Object,Object>(); map.put("label", conf.getLoc()); map.put("y", conf.getDeaths());dataPoints1.add(map);
			mapTotal = new HashMap<Object,Object>(); mapTotal.put("label", conf.getLoc()); mapTotal.put("y", conf.getTotalConfirmed());dataPoints2.add(mapTotal);
			mapDischarge = new HashMap<Object,Object>(); mapDischarge.put("label", conf.getLoc()); mapDischarge.put("y", conf.getDischarged());dataPoints3.add(mapDischarge);

		}
		list.add(dataPoints1);
		list2.add(dataPoints2);
		list3.add(dataPoints3);
		model.put("dataPointsList2", list);
		model.put("dataPointsList", list2);
		model.put("dataPointsList3", list3);
		return "login";
		
}
	private void compareList(History m, History k) {
		if(m.getLoc().equalsIgnoreCase(k.getLoc())) {
			m.setDeaths(m.getDeaths()-k.getDeaths());
			m.setTotalConfirmed(m.getTotalConfirmed()-k.getTotalConfirmed());
		}
		
	}

	@GetMapping("/cate={category}")
	public String getHistoryCovidReport(@PathVariable("category") String category,ModelMap model) throws IOException, URISyntaxException{
		List<Date> date = covidHistoryRepository.select();
		date.forEach(m->{
			try {
				m=Util.covertStringToDate(m.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		model.put("Date",date);
		System.out.println(model);
		return "historyDate";
		
}
		
}
