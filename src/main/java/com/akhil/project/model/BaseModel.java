package com.akhil.project.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {

	Boolean activeInd=Boolean.TRUE;
	public Boolean getActiveInd() {
		return activeInd;
	}
	public void setActiveInd(Boolean activeInd) {
		this.activeInd = activeInd;
	}
}