package gr.csd.uoc.cs360.tep.model;

import java.util.List;

public class Prescription {
	private Integer visitID;
	private String drug;
	
	public Integer getVisitID() {
		return visitID;
	}
	public void setVisitID(Integer visitID) {
		this.visitID = visitID;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
}
