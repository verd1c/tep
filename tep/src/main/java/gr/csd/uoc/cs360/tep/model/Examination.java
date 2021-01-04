package gr.csd.uoc.cs360.tep.model;

import java.util.List;

public class Examination {
	private Integer examinationID;
	private Integer visitID;
	private Integer AMKA;
	private Integer doctorID;
	private String diagnosis;
	private Boolean hospitalized;
	private List<Drug> drugs;
	private List<MedicalTest> tests;
	
	public Integer getExaminationID() {
		return examinationID;
	}
	public void setExaminationID(Integer examinationID) {
		this.examinationID = examinationID;
	}
	public Integer getAMKA() {
		return AMKA;
	}
	public void setAMKA(Integer aMKA) {
		AMKA = aMKA;
	}
	public Integer getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public Integer getVisitID() {
		return visitID;
	}
	public void setVisitID(Integer visitID) {
		this.visitID = visitID;
	}
	public List<Drug> getDrugs() {
		return drugs;
	}
	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}
	public List<MedicalTest> getTests() {
		return tests;
	}
	public void setTests(List<MedicalTest> tests) {
		this.tests = tests;
	}
	@Override
	public String toString() {
		return "Examination [examinationID=" + examinationID + ", visitID=" + visitID + ", AMKA=" + AMKA + ", doctorID="
				+ doctorID + ", diagnosis=" + diagnosis + ", hospitalized=" + hospitalized + ", drugs=" + drugs
				+ ", tests=" + tests + "]";
	}
	public boolean isHospitalized() {
		return hospitalized;
	}
	public void setHospitalized(boolean hospitalized) {
		this.hospitalized = hospitalized;
	}
}
