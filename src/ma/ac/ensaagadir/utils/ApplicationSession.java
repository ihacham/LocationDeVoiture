package ma.ac.ensaagadir.utils;

import java.io.File;

import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.models.Reservation;

public class ApplicationSession {
	
	private Client selectedClient;
	
	private Reservation selectedReservation;
	
	public String getAppFolder() {
		
		return System.getProperty("user.home") + File.separator + "LocationVoitureApp";
	}
	
	public String getPermisImagesFolder() {
		return getAppFolder() + File.separator + "PermisImages";
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	
	
	public Reservation getSelectedReservation() {
		return selectedReservation;
	}

	public void setSelectedReservation(Reservation selectedReservation) {
		this.selectedReservation = selectedReservation;
	}

	public ApplicationSession() {
	}
	
	
}
