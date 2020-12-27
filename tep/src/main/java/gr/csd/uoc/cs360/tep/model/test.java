package gr.csd.uoc.cs360.tep.model;

import gr.csd.uoc.cs360.tep.db.DoctorDB;
import gr.csd.uoc.cs360.tep.db.PatientDB;
import gr.csd.uoc.cs360.tep.db.UserDB;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Patient patient;
//		System.out.println(patient);
//		
		Patient me = new Patient();
		me.setAMKA(100);
		me.setFirstName("Aimee");
		me.setLastName("Marchand");
		me.setAddress("Deutschland");
		me.setInstitution("Ayaya");
		System.out.println(PatientDB.addPatient(me));
		
		patient = PatientDB.getPatient(100);
		System.out.println(patient);
//		System.out.println(UserDB.addUser("itmes", "hehe", "notpatient"));
		System.out.println(DoctorDB.getDoctor(10));
//		System.out.println(UserDB.getUser("a"));
//		System.out.println(UserDB.getUser("100"));
		Doctor doc = new Doctor();
		doc.setFirstName("johnny");
		doc.setLastName("sins");
		doc.setSpecialization("Cardiologist");
		DoctorDB.addDoctor(doc);
		System.out.println(UserDB.getUser("johnnysins"));
	}

}
