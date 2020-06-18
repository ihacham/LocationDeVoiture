package ma.ac.ensaagadir.utils;

import java.io.File;

import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.models.Contrat;
import ma.ac.ensaagadir.models.Facture;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.models.Utilisateur;
import ma.ac.ensaagadir.models.Voiture;

public class ApplicationSession {
	
	private Utilisateur connectedUser;
	
	private Client selectedClient;
	
	private Voiture selectedVoiture;
	
	private Reservation selectedReservation;
	
	private Contrat selectedContrat;
	
	private Facture selectedFacture;
	
	
	public String getAppFolder() {
		
		return System.getProperty("user.home") + File.separator + "LocationVoitureApp";
	}
	
	
	
	public Utilisateur getConnectedUser() {
		return connectedUser;
	}



	public void setConnectedUser(Utilisateur connectedUser) {
		this.connectedUser = connectedUser;
	}



	public String getPermisImagesFolder() {
		return getAppFolder() + File.separator + "PermisImages";
	}

	
	
	public Facture getSelectedFacture() {
		return selectedFacture;
	}

	public void setSelectedFacture(Facture selectedFacture) {
		this.selectedFacture = selectedFacture;
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
