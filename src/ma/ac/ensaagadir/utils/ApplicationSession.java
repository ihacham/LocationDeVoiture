package ma.ac.ensaagadir.utils;

import java.io.File;

import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.models.Contrat;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.models.Voiture;

public class ApplicationSession {
	
	private Client selectedClient;
	
	private Voiture selectedVoiture;
	
	private Reservation selectedReservation;
	
	private Contrat selectedContrat;
	
	
	
	
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
	
	

	public Voiture getSelectedVoiture() {
		return selectedVoiture;
	}

	public void setSelectedVoiture(Voiture selectedVoiture) {
		this.selectedVoiture = selectedVoiture;
	}

	
	
	public Contrat getSelectedContrat() {
		return selectedContrat;
	}

	public void setSelectedContrat(Contrat selectedContrat) {
		this.selectedContrat = selectedContrat;
	}

	public ApplicationSession() {
	}
	
	
}
