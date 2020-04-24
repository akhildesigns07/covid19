package com.akhil.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class History {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Date updatedDate;
	private String loc;
	private Integer confirmedCasesIndian;
	public History(){
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Integer getConfirmedCasesIndian() {
		return confirmedCasesIndian;
	}
	public void setConfirmedCasesIndian(Integer confirmedCasesIndian) {
		this.confirmedCasesIndian = confirmedCasesIndian;
	}
	public Integer getDischarged() {
		return discharged;
	}
	public void setDischarged(Integer discharged) {
		this.discharged = discharged;
	}
	public Integer getDeaths() {
		return deaths;
	}
	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}
	public Integer getConfirmedCasesForeign() {
		return confirmedCasesForeign;
	}
	public void setConfirmedCasesForeign(Integer confirmedCasesForeign) {
		this.confirmedCasesForeign = confirmedCasesForeign;
	}
	public Integer getTotalConfirmed() {
		return totalConfirmed;
	}
	public void setTotalConfirmed(Integer totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	private Integer discharged;
	private Integer deaths;
	private Integer confirmedCasesForeign;
	private Integer totalConfirmed;
}
