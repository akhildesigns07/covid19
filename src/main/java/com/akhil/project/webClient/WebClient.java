package com.akhil.project.webClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akhil.project.dto.CovidLatestResponse;
import com.akhil.project.dto.covidHistory.CovidHistoryResponse;

@Component
public class WebClient {
	@Value("${covid.latest}")
	String latest;
	@Value("${covid.history}")
	String history;
	
	public CovidLatestResponse getLatestValue() throws IOException, URISyntaxException {
		URI uri = new URI(latest);
		RestTemplate obj = new RestTemplate();
		ResponseEntity<CovidLatestResponse> response =null;
		response = obj.getForEntity(uri, CovidLatestResponse.class);	
		return response.getBody();
}
	public CovidHistoryResponse getHistoryValue() throws IOException, URISyntaxException {
		URI uri = new URI(history);
		System.out.println(history);
		RestTemplate obj = new RestTemplate();
		ResponseEntity<CovidHistoryResponse> response =null;
		response = obj.getForEntity(uri, CovidHistoryResponse.class);	
		return response.getBody();
}
	
}
