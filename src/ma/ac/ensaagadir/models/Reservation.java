package ma.ac.ensaagadir.models;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation {
	private  LongProperty  codeReservation ;
	private  ObjectProperty<LocalDate>  dateDepart  ;
	private  ObjectProperty<LocalDate>  dateRetour   ;
	private  ObjectProperty<LocalDate>  dateReservation  ;
	private   ObjectProperty<Voiture>  voiture ;
	private StringProperty etat; 
	private  ObjectProperty<Contrat>  contrat;
	private  ObjectProperty<RestituerVoiture>  restitution ;
	private ObjectProperty<Client> client;
	
	
	
	public Reservation() {
		voiture = new SimpleObjectProperty<Voiture>(new Voiture());
		contrat = new SimpleObjectProperty<Contrat>(new Contrat());
		client = new SimpleObjectProperty<Client>(new Client());
		restitution = new SimpleObjectProperty<RestituerVoiture>(new RestituerVoiture());
	}
	
	public Reservation( LocalDate dateReservation, LocalDate dateDepart, LocalDate dateRetour,String etat) {
		this.dateReservation = new SimpleObjectProperty<LocalDate>(dateReservation);
		this.dateDepart = new SimpleObjectProperty<LocalDate>(dateDepart);
		this.dateRetour = new SimpleObjectProperty<LocalDate>(dateRetour);
		this.etat = new SimpleStringProperty(etat);
	}
	public long getCodeReservation() {
		return codeReservation.get();
	}
	public LongProperty CodeReservation() {
		return codeReservation;
	}
	public void setCodeReservation(long codeReservation) {
		this.codeReservation = new SimpleLongProperty(codeReservation);
	}
	
	
	public String getEtat() {
		return etat.get();
	}
	public StringProperty Etat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = new SimpleStringProperty(etat);
	}

	public Voiture getVoiture() {
		return voiture.get();
	}
	public  ObjectProperty<Voiture> Voiture() {
		return voiture;
	}
	public void setVoiture(Voiture voiture) {
		this.voiture = new SimpleObjectProperty<>(voiture);
	}
	public RestituerVoiture getRestitution() {
		return restitution.get();
	}
	public  ObjectProperty<RestituerVoiture> Restitution() {
		return restitution;
	}
	public void setRestitution(RestituerVoiture restitution) {
		this.restitution = new SimpleObjectProperty<>(restitution);
	}
	public Contrat getContrat() {
		return contrat.get();
	}
	public  ObjectProperty<Contrat> Contrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = new SimpleObjectProperty<>(contrat);
	}
	public Client getClient() {
		return client.get();
	}
	public  ObjectProperty<Client> Client() {
		return client;
	}
	public void setClient(Client client) {
		this.client = new SimpleObjectProperty<>(client);
	}
	public LocalDate getDateReservation() {
		return dateReservation.get();
	}
	public  ObjectProperty<java.time.LocalDate> DateReservation() {
		return dateReservation;
	}
	public void setDateReservation(LocalDate dateReservation) {
		this.dateReservation = new SimpleObjectProperty<>(dateReservation);
	}
	public LocalDate getDateDepart() {
		return dateDepart.get();
	}
	public  ObjectProperty<java.time.LocalDate> DateDepart() {
		return dateDepart;
	}
	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = new SimpleObjectProperty<>(dateDepart);
	}
	public LocalDate getDateRetour() {
		return dateRetour.get();
	}
	public  ObjectProperty<java.time.LocalDate> DateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = new SimpleObjectProperty<>(dateRetour);
	}
	
	
	
	
}
