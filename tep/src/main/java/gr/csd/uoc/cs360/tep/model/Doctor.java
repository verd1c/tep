package gr.csd.uoc.cs360.tep.model;

import java.io.Serializable;

public class Doctor extends User implements Serializable {
	private String firstName;
	private String lastName;
	private Specialization specialization;
	
	/**
     * Enum for supporting job values
     */
    public enum Specialization {

        ALLERGIST("Allergist"), ANESTHESIOLOGIST("Anesthesiologist"), CARDIOLOGIST("Cardiologist"), SURGEON("Surgeon"), DERMATOLOGIST("Dermatologist"), GENERAL("General");
        private final String value;

        private Specialization(String value) {
            this.value = value;
        }

        /**
         * Returns string representation of value
         *
         * @return
         */
        @Override
        public String toString() {
            return this.value;
        }
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		switch (specialization.toLowerCase().trim()) {
        case "allergist":
            this.specialization = Specialization.ALLERGIST;
            break;
        case "anesthesiologist":
            this.specialization = Specialization.ANESTHESIOLOGIST;
            break;
        case "cardiologist":
            this.specialization = Specialization.CARDIOLOGIST;
            break;
        case "surgeon":
            this.specialization = Specialization.SURGEON;
            break;
        case "dermatologist":
            this.specialization = Specialization.DERMATOLOGIST;
            break;
        case "general":
        	this.specialization = Specialization.GENERAL;
        	break;
    }
	}
	
	@Override
	public String toString() {
		return "Doctor [firstName=" + firstName + ", lastName=" + lastName + ", specialization=" + specialization + "]";
	}
}
