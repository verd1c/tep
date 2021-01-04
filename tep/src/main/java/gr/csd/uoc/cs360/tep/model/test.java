package gr.csd.uoc.cs360.tep.model;

import java.util.ArrayList;
import java.util.List;

import gr.csd.uoc.cs360.tep.db.DoctorDB;
import gr.csd.uoc.cs360.tep.db.DrugDB;
import gr.csd.uoc.cs360.tep.db.ExaminationDB;
import gr.csd.uoc.cs360.tep.db.PatientDB;
import gr.csd.uoc.cs360.tep.db.ShiftDB;
import gr.csd.uoc.cs360.tep.db.UserDB;
import gr.csd.uoc.cs360.tep.db.VisitDB;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Patient patient;
//		System.out.println(patient);
//		
//		Patient me = new Patient();
//		me.setAMKA(100);
//		me.setFirstName("Aimee");
//		me.setLastName("Marchand");
//		me.setAddress("Deutschland");
//		me.setInstitution("Ayaya");
//		System.out.println(PatientDB.addPatient(me));
//		
//		patient = PatientDB.getPatient(100);
//		System.out.println(patient);
//		System.out.println(UserDB.addUser("itmes", "hehe", "notpatient"));
//		System.out.println(DoctorDB.getDoctor(10));
//		System.out.println(UserDB.getUser("a"));
//		System.out.println(UserDB.getUser("100"));
//		Doctor doc = new Doctor();
//		doc.setFirstName("Maria");
//		doc.setLastName("Dong");
//		doc.setSpecialization("Anesthesiologist");
//		DoctorDB.addDoctor(doc);
//		System.out.println(UserDB.getUser("johnnysins"));
//		Visit visit = new Visit();
//		visit.setAMKA(100);
//		visit.setIllness("fever");
//		VisitDB.makeVisit(visit);
//		List<Visit> visits = VisitDB.getVisits(100);
//		for(Visit visit : visits) {
//			System.out.println(visit);
//		}
		
		
//		// Make shift
//		List<User> attendees = new ArrayList<User>();
//		User johnny = new User();
//		johnny.setUserID(14);
//		User bob = new User();
//		bob.setUserID(17);
//		User nick = new User();
//		nick.setUserID(21);
//		User nik = new User();
//		nik.setUserID(18);
//		User a = new User();
//		a.setUserID(19);
//		User b = new User();
//		b.setUserID(20);
//		User c = new User();
//		c.setUserID(26);
//		
//		attendees.add(johnny);
//		attendees.add(bob);
//		attendees.add(nick);
//		attendees.add(nik);
//		attendees.add(a);
//		attendees.add(b);
//		attendees.add(c);
//		System.out.println(ShiftDB.updateShift(attendees));
		
//		System.out.println(DrugDB.getDrug(""));
		Examination examination = new Examination();
		examination.setVisitID(26);
		examination.setAMKA(312);
		examination.setDoctorID(14);
		examination.setDiagnosis("Good");
		
		List<Prescription> drugs = new ArrayList<>();
		Prescription d1 = new Prescription();
		d1.setDrug("Tylenol");
		Prescription d2 = new Prescription();
		d2.setDrug("Ibuprofen");
		
		drugs.add(d1);
		drugs.add(d2);
		//examination.setDrugs(drugs);
		List<MedicalTest> tests = new ArrayList<>();
		MedicalTest t1 = new MedicalTest();
		t1.setType("X-Ray");
		MedicalTest t2 = new MedicalTest();
		t2.setType("Urine Test");
		
		tests.add(t1);
		tests.add(t2);
		examination.setTests(tests);
		System.out.println(ExaminationDB.addExamination(examination));
		
	}

}
